package com.learningpulse.quiz.question_answer.question_pair_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.repository.QuestionPairCollectionAnswerRepository;
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
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionPairCollectionAnswer> getAllQuestionPairCollectionAnswersByUser(UUID sub) {
        List<QuestionPairCollectionAnswer> questionPairCollectionAnswers = questionPairCollectionAnswerRepository.findAllByCreatedBy(sub);
        if (questionPairCollectionAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NOT_FOUND);
        return questionPairCollectionAnswers;
    }

    public List<QuestionPairCollectionAnswer> getAllQuestionPairCollectionAnswers() {
        List<QuestionPairCollectionAnswer> questionPairCollectionAnswers = questionPairCollectionAnswerRepository.findAll();
        if (questionPairCollectionAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NOT_FOUND);
        return questionPairCollectionAnswers;
    }

    public List<QuestionPairCollectionAnswer> getAllQuestionPairCollectionAnswersByQuestionPairCollectionId(UUID questionPairCollectionId) {
        List<QuestionPairCollectionAnswer> questionPairCollectionAnswers = questionPairCollectionAnswerRepository.findAllByQuestionPairCollectionId(questionPairCollectionId);
        if (questionPairCollectionAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NOT_FOUND);
        return questionPairCollectionAnswers;
    }

    public QuestionPairCollectionAnswer createQuestionPairCollectionAnswer(UUID sub, @NotNull QuestionPairCollectionAnswer questionPairCollectionAnswer) {
        questionPairCollectionAnswer.setCreatedBy(sub);
        return questionPairCollectionAnswerRepository.save(questionPairCollectionAnswer);
    }

    public QuestionPairCollectionAnswer updateQuestionPairCollectionAnswer(@NotNull QuestionPairCollectionAnswer questionPairCollectionAnswer) {
        return questionPairCollectionAnswerRepository.findById(questionPairCollectionAnswer.getId())
                .map(q -> questionPairCollectionAnswerRepository.save(questionPairCollectionAnswer))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NOT_FOUND));
    }

    public QuestionPairCollectionAnswer deleteQuestionPairCollectionAnswer(UUID id) {
        return questionPairCollectionAnswerRepository.findById(id)
                .map(q -> {
                    questionPairCollectionAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NOT_FOUND));
    }

    // This is an easter egg lol
    public void deleteAllQuestionPairCollectionAnswersByQuestionPairCollectionId(UUID questionPairCollection_id) {
        List<QuestionPairCollectionAnswer> questionPairCollectionAnswers = questionPairCollectionAnswerRepository.findAllByQuestionPairCollectionId(questionPairCollection_id);
        if (questionPairCollectionAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionAnswer not found", HttpStatus.NOT_FOUND);
        questionPairCollectionAnswerRepository.deleteAll(questionPairCollectionAnswers);
    }
}
