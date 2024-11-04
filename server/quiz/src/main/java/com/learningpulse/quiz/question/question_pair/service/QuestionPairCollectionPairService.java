package com.learningpulse.quiz.question.question_pair.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPairRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionPairCollectionPairService {
    private final QuestionPairCollectionPairRepository questionPairCollectionPairRepository;

    public QuestionPairCollectionPair getQuestionPairCollectionPairById(UUID id) {
        return questionPairCollectionPairRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionPairCollectionPair> getAllQuestionPairCollectionPairsByUser(UUID sub) {
        List<QuestionPairCollectionPair> questionPairCollectionPairs = questionPairCollectionPairRepository.findAllByCreatedBy(sub);
        if (questionPairCollectionPairs.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND);
        return questionPairCollectionPairs;
    }

    public List<QuestionPairCollectionPair> getAllQuestionPairCollectionPairs() {
        List<QuestionPairCollectionPair> questionPairCollectionPairs = questionPairCollectionPairRepository.findAll();
        if (questionPairCollectionPairs.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND);
        return questionPairCollectionPairs;
    }

    public List<QuestionPairCollectionPair> getAllQuestionPairCollectionPairsByQuestion(UUID questionPairId) {
        List<QuestionPairCollectionPair> questionPairCollectionPairs = questionPairCollectionPairRepository.findAllByQuestionPairCollectionId(questionPairId);
        if (questionPairCollectionPairs.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND);
        return questionPairCollectionPairs;
    }

    public QuestionPairCollectionPair createQuestionPairCollectionPair(UUID sub, @NotNull QuestionPairCollectionPair questionPairCollectionPair) {
        questionPairCollectionPair.setCreatedBy(sub);
        return questionPairCollectionPairRepository.save(questionPairCollectionPair);
    }

    public QuestionPairCollectionPair updateQuestionPairCollectionPair(@NotNull QuestionPairCollectionPair questionPairCollectionPair) {
        return questionPairCollectionPairRepository.findById(questionPairCollectionPair.getId())
                .map(q -> questionPairCollectionPairRepository.save(questionPairCollectionPair))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND));
    }

    public QuestionPairCollectionPair deleteQuestionPairCollectionPair(UUID id) {
        return questionPairCollectionPairRepository.findById(id)
                .map(q -> {
                    questionPairCollectionPairRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND));

    }
}
