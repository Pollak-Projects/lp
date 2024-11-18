package com.learningpulse.quiz.question_answer.question_pair_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_pair_answer.QuestionPairCollectionPairAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_pair_answer.QuestionPairCollectionPairAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionPairAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionPairAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionPairOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.repository.QuestionPairCollectionPairAnswerRepository;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionPairCollectionPairAnswerService {
    private final QuestionPairCollectionPairAnswerRepository questionPairCollectionPairAnswerRepository;

    public QuestionPairCollectionPairAnswer getQuestionPairCollectionPairAnswerById(UUID id) {
        return questionPairCollectionPairAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionPairAnswer not found", HttpStatus.NO_CONTENT));
    }

    public List<QuestionPairCollectionPairAnswer> getAllQuestionPairCollectionPairAnswersByUser(UUID sub) {
        List<QuestionPairCollectionPairAnswer> questionPairCollectionPairAnswers = questionPairCollectionPairAnswerRepository.findAllByCreatedBy(sub);
        if (questionPairCollectionPairAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionPairAnswer not found", HttpStatus.NO_CONTENT);
        return questionPairCollectionPairAnswers;
    }

    public List<QuestionPairCollectionPairAnswer> getAllQuestionPairCollectionPairAnswers() {
        List<QuestionPairCollectionPairAnswer> questionPairCollectionPairAnswers = questionPairCollectionPairAnswerRepository.findAll();
        if (questionPairCollectionPairAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionPairAnswer not found", HttpStatus.NO_CONTENT);
        return questionPairCollectionPairAnswers;
    }

    public QuestionPairCollectionPairAnswer createQuestionPairCollectionPairAnswer(UUID sub, @NotNull QuestionPairCollectionPairAnswerCreateDTO dto) {
        QuestionPairCollectionPairAnswer questionPairCollectionPairAnswer = QuestionPairCollectionPairAnswer.builder()
                .createdBy(sub)
                .questionPairCollectionAnswer(QuestionPairCollectionAnswer.builder().id(dto.questionPairCollectionAnswerId()).build())
                .questionPairCollectionPair(QuestionPairCollectionPair.builder().id(dto.questionPairCollectionPairId()).build())
                .left(QuestionPairCollectionPairOptionsAnswer.builder()
                        .content(dto.left().content()).build())
                .right(QuestionPairCollectionPairOptionsAnswer.builder()
                        .content(dto.right().content()).build())
                .build();
        return questionPairCollectionPairAnswerRepository.save(questionPairCollectionPairAnswer);
    }

    public QuestionPairCollectionPairAnswer updateQuestionPairCollectionPairAnswer(@NotNull QuestionPairCollectionPairAnswerUpdateDTO dto) {
        return questionPairCollectionPairAnswerRepository.findById(dto.questionPairCollectionPairAnswerId())
                .map(q -> {
                    if (dto.questionPairCollectionPairId() != null)
                        q.setQuestionPairCollectionAnswer(QuestionPairCollectionAnswer.builder().id(dto.questionPairCollectionPairAnswerId()).build());
                    if (dto.left() != null)
                        q.setLeft(QuestionPairCollectionPairOptionsAnswer.builder()
                                .id(dto.left()).build());
                    if (dto.right() != null)
                        q.setRight(QuestionPairCollectionPairOptionsAnswer.builder()
                                .id(dto.right()).build());
                    return questionPairCollectionPairAnswerRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionPairAnswer not found", HttpStatus.NO_CONTENT));
    }

    public QuestionPairCollectionPairAnswer deleteQuestionPairCollectionPairAnswer(UUID id) {
        return questionPairCollectionPairAnswerRepository.findById(id)
                .map(q -> {
                    questionPairCollectionPairAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionPairAnswer not found", HttpStatus.NO_CONTENT));
    }

    // This is an easter egg lol
    //    public void deleteAllQuestionPairCollectionPairAnswersByQuestionPairCollectionId(UUID questionPairCollection_id) {
    //        List<QuestionPairCollectionPairAnswer> questionPairCollectionPairAnswers = questionPairCollectionPairAnswerRepository.findAllByQuestionPairCollectionId(questionPairCollection_id);
    //        if (questionPairCollectionPairAnswers.isEmpty())
    //            throw new HttpStatusCodeException("QuestionPairCollectionPairAnswer not found", HttpStatus.NO_CONTENT);
    //        questionPairCollectionPairAnswerRepository.deleteAll(questionPairCollectionPairAnswers);
    //    }
}
