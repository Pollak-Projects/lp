package com.learningpulse.quiz.quiz;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import com.learningpulse.quiz.question.question_file.model.QuestionFile;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPairOptions;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.quiz.dto.QuizCreateDTO;
import com.learningpulse.quiz.quiz.dto.QuizFullCreateDTO;
import com.learningpulse.quiz.quiz.dto.QuizUpdateDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final WebClient webClient;

    public Mono<UserDTO> webclient() {

        return webClient
                .get()
                .uri("http://localhost:8181/api/v1/user/webclient")
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("okta"))
                .retrieve()
                .bodyToMono(UserDTO.class);
    }

    public Quiz getQuizById(UUID id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("Quiz not found", HttpStatus.NOT_FOUND));
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        if (quizzes.isEmpty())
            throw new HttpStatusCodeException("Quiz not found", HttpStatus.NOT_FOUND);
        return quizzes;
    }

    public Quiz createQuiz(UUID sub, @NotNull QuizCreateDTO quizCreateDTO) {
        return quizRepository.save(Quiz.builder()
                .createdBy(sub)
                .name(quizCreateDTO.name())
                .description(quizCreateDTO.description())
                .deadline(quizCreateDTO.deadline())
                .viewAfterSubmission(quizCreateDTO.viewAfterSubmission())
                .build());
    }

    public Quiz createFullQuiz(UUID sub, @NotNull QuizFullCreateDTO quizFullCreateDTO) {
        Quiz quiz = Quiz.builder()
                .createdBy(sub)
                .name(quizFullCreateDTO.name())
                .description(quizFullCreateDTO.description())
                .deadline(quizFullCreateDTO.deadline())
                .viewAfterSubmission(quizFullCreateDTO.viewAfterSubmission())
                .build();

        if (quizFullCreateDTO.questionCheckboxes() != null && !quizFullCreateDTO.questionCheckboxes().isEmpty())
            quiz.setQuestionCheckboxes(quizFullCreateDTO.questionCheckboxes().stream().map(q -> {
                QuestionCheckbox questionCheckbox = QuestionCheckbox.builder()
                        .createdBy(sub)
                        .quiz(quiz)
                        .title(q.title())
                        .build();

                questionCheckbox.setOptions(q.options().stream().map(o ->
                        QuestionCheckboxOptions.builder()
                                .createdBy(sub)
                                .name(o.name())
                                .answer(o.answer())
                                .questionCheckbox(questionCheckbox)
                                .build()).toList());

                return questionCheckbox;
            }).toList());

        if (quizFullCreateDTO.questionFiles() != null && !quizFullCreateDTO.questionFiles().isEmpty())
            quiz.setQuestionFiles(quizFullCreateDTO.questionFiles().stream().map(q -> QuestionFile.builder()
                    .createdBy(sub)
                    .quiz(quiz)
                    .build()
            ).toList());


        if (quizFullCreateDTO.questionOrders() != null && !quizFullCreateDTO.questionOrders().isEmpty())
            quiz.setQuestionOrders(quizFullCreateDTO.questionOrders().stream().map(q -> {
                QuestionOrder questionOrder = QuestionOrder.builder()
                        .createdBy(sub)
                        .quiz(quiz)
                        .title(q.title())
                        .build();

                questionOrder.setOptions(q.options().stream().map(o ->
                        QuestionOrderOptions.builder()
                                .createdBy(sub)
                                .title(o.title())
                                .place(o.place())
                                .questionOrder(questionOrder)
                                .build()).toList());

                return questionOrder;
            }).toList());


        if (quizFullCreateDTO.questionPairCollections() != null && !quizFullCreateDTO.questionPairCollections().isEmpty())
            quiz.setQuestionPairCollections(quizFullCreateDTO.questionPairCollections().stream().map(q -> {
                QuestionPairCollection questionPairCollection = QuestionPairCollection.builder()
                        .createdBy(sub)
                        .quiz(quiz)
                        .title(q.title())
                        .build();

                questionPairCollection.setPairs(q.pairs().stream().map(p -> {
                    QuestionPairCollectionPair questionPairCollectionPair = QuestionPairCollectionPair.builder()
                            .createdBy(sub)
                            .belongsTo(questionPairCollection)
                            .build();

                    QuestionPairCollectionPairOptions left = QuestionPairCollectionPairOptions.builder()
                            .content(p.left().content())
                            .build();
                    questionPairCollectionPair.setLeft(left);

                    QuestionPairCollectionPairOptions right = QuestionPairCollectionPairOptions.builder()
                            .content(p.right().content())
                            .build();
                    questionPairCollectionPair.setRight(right);

                    return questionPairCollectionPair;
                }).toList());

                return questionPairCollection;
            }).toList());


        if (quizFullCreateDTO.questionRadios() != null && !quizFullCreateDTO.questionRadios().isEmpty())
            quiz.setQuestionRadios(quizFullCreateDTO.questionRadios().stream().map(q -> {
                QuestionRadio questionRadio = QuestionRadio.builder()
                        .createdBy(sub)
                        .quiz(quiz)
                        .title(q.title())
                        .build();

                questionRadio.setOptions(q.options().stream().map(o ->
                        QuestionRadioOptions.builder()
                                .createdBy(sub)
                                .questionRadio(questionRadio)
                                .title(o.title())
                                .answer(o.answer())
                                .build()).toList());

                return questionRadio;
            }).toList());


        if (quizFullCreateDTO.questionTexts() != null && !quizFullCreateDTO.questionTexts().isEmpty())
            quiz.setQuestionTexts(quizFullCreateDTO.questionTexts().stream().map(q -> QuestionText.builder()
                    .createdBy(sub)
                    .quiz(quiz)
                    .title(q.title())
                    .answer(q.answer())
                    .build()).toList());

        return quizRepository.save(quiz);
    }

    @Transactional
    public Quiz updateQuiz(@NotNull QuizUpdateDTO quizUpdateDTO) {
        return quizRepository.findById(quizUpdateDTO.quizId())
                .map(q -> {
                    if (quizUpdateDTO.name() != null)
                        q.setName(quizUpdateDTO.name());
                    if (quizUpdateDTO.description() != null)
                        q.setDescription(quizUpdateDTO.description());
                    if (quizUpdateDTO.deadline() != null)
                        q.setDeadline(quizUpdateDTO.deadline());
                    if (quizUpdateDTO.viewAfterSubmission() != null)
                        q.setViewAfterSubmission(quizUpdateDTO.viewAfterSubmission());
                    if (quizUpdateDTO.questionCheckboxes() != null && !quizUpdateDTO.questionCheckboxes().isEmpty())
                        q.setQuestionCheckboxes(quizUpdateDTO.questionCheckboxes().stream().map(qcb -> QuestionCheckbox.builder()
                                .id(qcb)
                                .build()).toList());
                    if (quizUpdateDTO.questionFiles() != null && !quizUpdateDTO.questionFiles().isEmpty())
                        q.setQuestionFiles(quizUpdateDTO.questionFiles().stream().map(qf -> QuestionFile.builder()
                                .id(qf)
                                .build()).toList());
                    if (quizUpdateDTO.questionOrders() != null && !quizUpdateDTO.questionOrders().isEmpty())
                        q.setQuestionOrders(quizUpdateDTO.questionOrders().stream().map(qo -> QuestionOrder.builder()
                                .id(qo)
                                .build()).toList());
                    if (quizUpdateDTO.questionPairCollections() != null && !quizUpdateDTO.questionPairCollections().isEmpty())
                        q.setQuestionPairCollections(quizUpdateDTO.questionPairCollections().stream().map(qpc -> QuestionPairCollection.builder()
                                .id(qpc)
                                .build()).toList());
                    if (quizUpdateDTO.questionRadios() != null && !quizUpdateDTO.questionRadios().isEmpty())
                        q.setQuestionRadios(quizUpdateDTO.questionRadios().stream().map(qr -> QuestionRadio.builder()
                                .id(qr)
                                .build()).toList());
                    if (quizUpdateDTO.questionTexts() != null && !quizUpdateDTO.questionTexts().isEmpty())
                        q.setQuestionTexts(quizUpdateDTO.questionTexts().stream().map(qt -> QuestionText.builder()
                                .id(qt)
                                .build()).toList());
                    return quizRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("Quiz not found", HttpStatus.NOT_FOUND));
    }

    public Quiz deleteQuiz(UUID id) {
        return quizRepository.findById(id)
                .map(q -> {
                    quizRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("Quiz not found", HttpStatus.NOT_FOUND));
    }
}
