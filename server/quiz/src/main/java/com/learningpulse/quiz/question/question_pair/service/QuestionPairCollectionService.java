package com.learningpulse.quiz.question.question_pair.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionPairCollectionService {
    private final QuestionPairCollectionRepository questionPairCollectionRepository;

    public QuestionPairCollection getQuestionPairCollectionById(UUID id) {
        return questionPairCollectionRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionPairCollection> getAllQuestionPairCollectionsByUser(UUID sub) {
        List<QuestionPairCollection> questionPairCollections = questionPairCollectionRepository.findAllByCreatedBy(sub);
        if (questionPairCollections.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND);
        return questionPairCollections;
    }

    public List<QuestionPairCollection> getAllQuestionPairCollections() {
        List<QuestionPairCollection> questionPairCollections = questionPairCollectionRepository.findAll();
        if (questionPairCollections.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND);
        return questionPairCollections;
    }

    public QuestionPairCollection createQuestionPairCollection(UUID sub, QuestionPairCollection questionPairCollection) {
        questionPairCollection.setCreatedBy(sub);
        return questionPairCollectionRepository.save(questionPairCollection);
    }

    public QuestionPairCollection updateQuestionPairCollection(@NotNull QuestionPairCollection questionPairCollection) {
        return questionPairCollectionRepository.findById(questionPairCollection.getId())
                .map(q -> questionPairCollectionRepository.save(questionPairCollection))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND));
    }

    public QuestionPairCollection deleteQuestionPairCollection(UUID id) {
        return questionPairCollectionRepository.findById(id)
                .map(q -> {
                    questionPairCollectionRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND));
    }
}
