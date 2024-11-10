package com.learningpulse.quiz.quiz_answer;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import com.learningpulse.quiz.question.question_file.model.QuestionFile;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxAnswer;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_file_answer.model.QuestionFileAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionPairAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionPairOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_text_answer.model.QuestionTextAnswer;
import com.learningpulse.quiz.quiz.Quiz;
import com.learningpulse.quiz.quiz_answer.dto.QuizAnswerCreateDTO;
import com.learningpulse.quiz.quiz_answer.dto.QuizAnswerFullCreateDTO;
import com.learningpulse.quiz.quiz_answer.dto.QuizAnswerUpdateDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizAnswerService {
    private final QuizAnswerRepository quizAnswerRepository;

    public QuizAnswer getQuizAnswerById(UUID id) {
        return quizAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND));
    }

    public List<QuizAnswer> getAllQuizAnswersByUser(UUID sub) {
        List<QuizAnswer> quizAnswers = quizAnswerRepository.findAllByCreatedBy(sub);
        if (quizAnswers.isEmpty())
            throw new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND);
        return quizAnswers;
    }

    public List<QuizAnswer> getAllQuizAnswers() {
        List<QuizAnswer> quizAnswers = quizAnswerRepository.findAll();
        if (quizAnswers.isEmpty())
            throw new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND);
        return quizAnswers;
    }

    public QuizAnswer createQuizAnswer(UUID sub, @NotNull QuizAnswerCreateDTO quizAnswerCreateDTO) {
        return quizAnswerRepository.save(QuizAnswer.builder()
                .createdBy(sub)
                .quiz(Quiz.builder().id(quizAnswerCreateDTO.quizId()).build())
                .build());
    }

    @Transactional
    public QuizAnswer createFullQuizAnswer(UUID sub, @NotNull QuizAnswerFullCreateDTO quizAnswerFullCreateDTO) {
        QuizAnswer quizAnswer = QuizAnswer.builder()
                .createdBy(sub)
                .quiz(Quiz.builder().id(quizAnswerFullCreateDTO.quizId()).build())
                .build();

        if (quizAnswerFullCreateDTO.questionCheckboxAnswers() != null && !quizAnswerFullCreateDTO.questionCheckboxAnswers().isEmpty())
            quizAnswer.setQuestionCheckboxAnswers(quizAnswerFullCreateDTO.questionCheckboxAnswers().stream().map(qca -> {
                QuestionCheckboxAnswer questionCheckboxAnswer = QuestionCheckboxAnswer.builder()
                        .createdBy(sub)
                        .belongsTo(quizAnswer)
                        .build();

                questionCheckboxAnswer.setOptions(qca.options().stream().map(o -> QuestionCheckboxOptionsAnswer.builder()
                        .createdBy(sub)
                        .questionCheckboxAnswer(questionCheckboxAnswer)
                        .questionCheckboxOptions(QuestionCheckboxOptions.builder().id(o.questionCheckboxOptionsId()).build())
                        .answer(o.answer())
                        .build()
                ).toList());

                return questionCheckboxAnswer;
            }).toList());


        if (quizAnswerFullCreateDTO.questionFileAnswers() != null && !quizAnswerFullCreateDTO.questionFileAnswers().isEmpty())
            quizAnswer.setQuestionFileAnswers(quizAnswerFullCreateDTO.questionFileAnswers().stream().map(qfa -> QuestionFileAnswer.builder()
                    .createdBy(sub)
                    .belongsTo(quizAnswer)
                    .questionFile(QuestionFile.builder().id(qfa.questionFileId()).build())
                    .build()
            ).toList());


        if (quizAnswerFullCreateDTO.questionOrderAnswers() != null && !quizAnswerFullCreateDTO.questionOrderAnswers().isEmpty())
            quizAnswer.setQuestionOrderAnswers(quizAnswerFullCreateDTO.questionOrderAnswers().stream().map(qoa -> {
                QuestionOrderAnswer questionOrderAnswer = QuestionOrderAnswer.builder()
                        .createdBy(sub)
                        .belongsTo(quizAnswer)
                        .build();

                questionOrderAnswer.setOptions(qoa.options().stream().map(o -> QuestionOrderOptionsAnswer.builder()
                        .createdBy(sub)
                        .questionOrderAnswer(questionOrderAnswer)
                        .questionOrderOptions(QuestionOrderOptions.builder().id(o.questionOrderOptionsId()).build())
                        .place(o.place())
                        .build()).toList());

                return questionOrderAnswer;
            }).toList());


        if (quizAnswerFullCreateDTO.questionPairCollectionAnswers() != null && !quizAnswerFullCreateDTO.questionPairCollectionAnswers().isEmpty())
            quizAnswer.setQuestionPairCollectionAnswers(quizAnswerFullCreateDTO.questionPairCollectionAnswers().stream().map(qpca -> {
                QuestionPairCollectionAnswer questionPairCollectionAnswer = QuestionPairCollectionAnswer.builder()
                        .createdBy(sub)
                        .belongsTo(quizAnswer)
                        .build();

                questionPairCollectionAnswer.setPairs(qpca.pairs().stream().map(p -> {
                    QuestionPairCollectionPairAnswer questionPairCollectionPairAnswer = QuestionPairCollectionPairAnswer.builder()
                            .createdBy(sub)
                            .questionPairCollectionAnswer(questionPairCollectionAnswer)
                            .questionPairCollectionPair(QuestionPairCollectionPair.builder().id(p.questionPairCollectionPairId()).build())
                            .build();

                    questionPairCollectionPairAnswer.setLeft(QuestionPairCollectionPairOptionsAnswer.builder()
                            .questionPairCollectionPairAnswer(questionPairCollectionPairAnswer)
                            .content(p.left().content())
                            .build());

                    questionPairCollectionPairAnswer.setRight(QuestionPairCollectionPairOptionsAnswer.builder()
                            .questionPairCollectionPairAnswer(questionPairCollectionPairAnswer)
                            .content(p.right().content())
                            .build());

                    return questionPairCollectionPairAnswer;
                }).toList());

                return questionPairCollectionAnswer;
            }).toList());


        if (quizAnswerFullCreateDTO.questionRadioAnswers() != null && !quizAnswerFullCreateDTO.questionRadioAnswers().isEmpty())
            quizAnswer.setQuestionRadioAnswers(quizAnswerFullCreateDTO.questionRadioAnswers().stream().map(qra -> {
                QuestionRadioAnswer questionRadioAnswer = QuestionRadioAnswer.builder()
                        .createdBy(sub)
                        .belongsTo(quizAnswer)
                        .questionRadio(QuestionRadio.builder().id(qra.questionRadioId()).build())
                        .build();

                questionRadioAnswer.setOptions(qra.options().stream().map(o -> QuestionRadioOptionsAnswer.builder()
                        .createdBy(sub)
                        .questionRadioAnswer(questionRadioAnswer)
                        .questionRadioOptions(QuestionRadioOptions.builder().id(o.questionRadioOptionsId()).build())
                        .answer(o.answer())
                        .build()).toList());

                return questionRadioAnswer;
            }).toList());


        if (quizAnswerFullCreateDTO.questionTextAnswers() != null && !quizAnswerFullCreateDTO.questionTextAnswers().isEmpty())
            quizAnswer.setQuestionTextAnswers(quizAnswerFullCreateDTO.questionTextAnswers().stream().map(qta -> QuestionTextAnswer.builder()
                    .createdBy(sub)
                    .belongsTo(quizAnswer)
                    .questionText(QuestionText.builder().id(qta.questionTextId()).build())
                    .answer(qta.answer())
                    .build()).toList());


        return quizAnswerRepository.save(quizAnswer);
    }

    @Transactional
    public QuizAnswer updateQuizAnswer(@NotNull QuizAnswerUpdateDTO quizAnswerUpdateDTO) {
        return quizAnswerRepository.findById(quizAnswerUpdateDTO.quizAnswerId())
                .map(q -> {
                    if (quizAnswerUpdateDTO.quizId() != null)
                        q.setQuiz(Quiz.builder().id(quizAnswerUpdateDTO.quizId()).build());
                    if (quizAnswerUpdateDTO.questionCheckboxAnswers() != null && !quizAnswerUpdateDTO.questionCheckboxAnswers().isEmpty())
                        q.setQuestionCheckboxAnswers(quizAnswerUpdateDTO.questionCheckboxAnswers().stream().map(qca ->
                                QuestionCheckboxAnswer.builder()
                                        .id(qca)
                                        .build()).toList());
                    if (quizAnswerUpdateDTO.questionFileAnswers() != null && !quizAnswerUpdateDTO.questionFileAnswers().isEmpty())
                        q.setQuestionFileAnswers(quizAnswerUpdateDTO.questionFileAnswers().stream().map(qfa ->
                                QuestionFileAnswer.builder()
                                        .id(qfa)
                                        .build()).toList());
                    if (quizAnswerUpdateDTO.questionOrderAnswers() != null && !quizAnswerUpdateDTO.questionOrderAnswers().isEmpty())
                        q.setQuestionOrderAnswers(quizAnswerUpdateDTO.questionOrderAnswers().stream().map(qoa ->
                                QuestionOrderAnswer.builder()
                                        .id(qoa)
                                        .build()).toList());
                    if (quizAnswerUpdateDTO.questionPairCollectionAnswers() != null && !quizAnswerUpdateDTO.questionPairCollectionAnswers().isEmpty())
                        q.setQuestionPairCollectionAnswers(quizAnswerUpdateDTO.questionPairCollectionAnswers().stream().map(qpca ->
                                QuestionPairCollectionAnswer.builder()
                                        .id(qpca)
                                        .build()).toList());
                    if (quizAnswerUpdateDTO.questionRadioAnswers() != null && !quizAnswerUpdateDTO.questionRadioAnswers().isEmpty())
                        q.setQuestionRadioAnswers(quizAnswerUpdateDTO.questionRadioAnswers().stream().map(qra ->
                                QuestionRadioAnswer.builder()
                                        .id(qra)
                                        .build()).toList());
                    if (quizAnswerUpdateDTO.questionTextAnswers() != null && !quizAnswerUpdateDTO.questionTextAnswers().isEmpty())
                        q.setQuestionTextAnswers(quizAnswerUpdateDTO.questionTextAnswers().stream().map(qta ->
                                QuestionTextAnswer.builder()
                                        .id(qta)
                                        .build()).toList());
                    return quizAnswerRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND));
    }

    public QuizAnswer deleteQuizAnswer(UUID id) {
        return quizAnswerRepository.findById(id)
                .map(q -> {
                    quizAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND));
    }
}
