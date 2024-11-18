package com.learningpulse.quiz.question_answer.question_pair_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_answer.QuestionPairCollectionAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_answer.QuestionPairCollectionAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionPairAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionPairOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.repository.QuestionPairCollectionAnswerRepository;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionPairCollectionAnswerService {
    private final QuestionPairCollectionAnswerRepository questionPairCollectionAnswerRepository;

    public QuestionPairCollectionAnswer getQuestionPairCollectionAnswerById(UUID id) {
        return questionPairCollectionAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NO_CONTENT));
    }

    public List<QuestionPairCollectionAnswer> getAllQuestionPairCollectionAnswersByUser(UUID sub) {
        List<QuestionPairCollectionAnswer> questionPairCollectionAnswers = questionPairCollectionAnswerRepository.findAllByCreatedBy(sub);
        if (questionPairCollectionAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NO_CONTENT);
        return questionPairCollectionAnswers;
    }

    public List<QuestionPairCollectionAnswer> getAllQuestionPairCollectionAnswers() {
        List<QuestionPairCollectionAnswer> questionPairCollectionAnswers = questionPairCollectionAnswerRepository.findAll();
        if (questionPairCollectionAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NO_CONTENT);
        return questionPairCollectionAnswers;
    }

    public QuestionPairCollectionAnswer createQuestionPairCollectionAnswer(UUID sub, @NotNull QuestionPairCollectionAnswerCreateDTO dto) {
        QuestionPairCollectionAnswer questionPairCollectionAnswer = QuestionPairCollectionAnswer.builder()
                .createdBy(sub)
                .belongsTo(QuizAnswer.builder().id(dto.quizAnswerId()).build())
                .questionPairCollection(QuestionPairCollection.builder().id(dto.questionPairCollectionId()).build())
                .build();

        questionPairCollectionAnswer.setAnswerPairs(dto.pairs().stream().map(p -> QuestionPairCollectionPairAnswer.builder()
                .createdBy(sub)
                .questionPairCollectionAnswer(questionPairCollectionAnswer)
                .questionPairCollectionPair(QuestionPairCollectionPair.builder().id(p.questionPairCollectionPairId()).build())
                .left(QuestionPairCollectionPairOptionsAnswer.builder()
                        .content(p.left().content()).build())
                .right(QuestionPairCollectionPairOptionsAnswer.builder()
                        .content(p.right().content()).build())
                .build()).toList());

        return questionPairCollectionAnswerRepository.save(questionPairCollectionAnswer);
    }

    public QuestionPairCollectionAnswer updateQuestionPairCollectionAnswer(@NotNull QuestionPairCollectionAnswerUpdateDTO dto) {
        return questionPairCollectionAnswerRepository.findById(dto.questionPairCollectionAnswerId())
                .map(q -> {
                    if (dto.quizAnswerId() != null)
                        q.setBelongsTo(QuizAnswer.builder().id(dto.quizAnswerId()).build());
                    if (dto.questionPairCollectionId() != null)
                        q.setQuestionPairCollection(QuestionPairCollection.builder().id(dto.questionPairCollectionId()).build());
                    if (dto.pairs() != null && !dto.pairs().isEmpty())
                        q.setAnswerPairs(dto.pairs().stream().map(p -> QuestionPairCollectionPairAnswer.builder()
                                .id(p)
                                .build()).toList());
                    return questionPairCollectionAnswerRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NO_CONTENT));
    }

    public QuestionPairCollectionAnswer deleteQuestionPairCollectionAnswer(UUID id) {
        return questionPairCollectionAnswerRepository.findById(id)
                .map(q -> {
                    questionPairCollectionAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NO_CONTENT));
    }

    // This is an easter egg lol
    //    public void deleteAllQuestionPairCollectionAnswersByQuestionPairCollectionId(UUID questionPairCollection_id) {
    //        List<QuestionPairCollectionAnswer> questionPairCollectionAnswers = questionPairCollectionAnswerRepository.findAllByQuestionPairCollectionId(questionPairCollection_id);
    //        if (questionPairCollectionAnswers.isEmpty())
    //            throw new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NO_CONTENT);
    //        questionPairCollectionAnswerRepository.deleteAll(questionPairCollectionAnswers);
    //    }
}
