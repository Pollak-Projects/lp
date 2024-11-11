package com.learningpulse.quiz.question.question_pair.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_pair.dto.question_pair_collection_pair.QuestionPairCollectionPairCreateDTO;
import com.learningpulse.quiz.question.question_pair.dto.question_pair_collection_pair.QuestionPairCollectionPairUpdateDTO;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPairOptions;
import com.learningpulse.quiz.question.question_pair.repository.QuestionPairCollectionPairRepository;
import jakarta.transaction.Transactional;
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
        List<QuestionPairCollectionPair> questionPairCollectionPairs = questionPairCollectionPairRepository.findAllByBelongsToId(questionPairId);
        if (questionPairCollectionPairs.isEmpty())
            throw new HttpStatusCodeException("QuestionPairCollectionPair not found", HttpStatus.NOT_FOUND);
        return questionPairCollectionPairs;
    }

    public QuestionPairCollectionPair createQuestionPairCollectionPair(UUID sub, @NotNull QuestionPairCollectionPairCreateDTO dto) {
        QuestionPairCollectionPair questionPairCollectionPair = QuestionPairCollectionPair.builder()
                .createdBy(sub)
                .belongsTo(QuestionPairCollection.builder().id(dto.belongsToQuestionPairCollectionId()).build())
                .left(QuestionPairCollectionPairOptions.builder()
                        .content(dto.left().content()).build())
                .right(QuestionPairCollectionPairOptions.builder()
                        .content(dto.right().content()).build())
                .build();

        return questionPairCollectionPairRepository.save(questionPairCollectionPair);
    }

    @Transactional
    public QuestionPairCollectionPair updateQuestionPairCollectionPair(@NotNull QuestionPairCollectionPairUpdateDTO dto) {
        return questionPairCollectionPairRepository.findById(dto.questionPairCollectionPairId())
                .map(q -> {
                    if (dto.belongsToQuestionPairCollectionId() != null)
                        q.setBelongsTo(QuestionPairCollection.builder().id(dto.belongsToQuestionPairCollectionId()).build());
                    if (dto.rightQuestionPairOptionsId() != null)
                        q.setLeft(QuestionPairCollectionPairOptions.builder()
                                .id(dto.leftQuestionPairOptionsId()).build());
                    if (dto.rightQuestionPairOptionsId() != null)
                        q.setRight(QuestionPairCollectionPairOptions.builder()
                                .id(dto.rightQuestionPairOptionsId()).build());
                    return questionPairCollectionPairRepository.save(q);
                })
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
