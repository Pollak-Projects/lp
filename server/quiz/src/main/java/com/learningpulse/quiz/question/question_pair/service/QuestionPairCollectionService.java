package com.learningpulse.quiz.question.question_pair.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_pair.dto.question_pair_collection.QuestionPairCollectionCreateDTO;
import com.learningpulse.quiz.question.question_pair.dto.question_pair_collection.QuestionPairCollectionUpdateDTO;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPairOptions;
import com.learningpulse.quiz.question.question_pair.repository.QuestionPairCollectionRepository;
import com.learningpulse.quiz.quiz.Quiz;
import jakarta.transaction.Transactional;
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

    public QuestionPairCollection createQuestionPairCollection(UUID sub, @NotNull QuestionPairCollectionCreateDTO dto) {
        QuestionPairCollection questionPairCollection = QuestionPairCollection.builder()
                .createdBy(sub)
                .quiz(Quiz.builder().id(dto.quizId()).build())
                .title(dto.title())
                .build();

        questionPairCollection.setPairs(dto.pairs().stream().map(p -> {
            QuestionPairCollectionPair questionPairCollectionPair = QuestionPairCollectionPair.builder()
                    .createdBy(sub)
                    .belongsTo(questionPairCollection)
                    .build();

            questionPairCollectionPair.setLeft(QuestionPairCollectionPairOptions.builder()
                    .content(p.left().content()).build());

            questionPairCollectionPair.setRight(QuestionPairCollectionPairOptions.builder()
                    .content(p.right().content()).build());

            return questionPairCollectionPair;
        }).toList());

        return questionPairCollectionRepository.save(questionPairCollection);
    }

    @Transactional
    public QuestionPairCollection updateQuestionPairCollection(@NotNull QuestionPairCollectionUpdateDTO dto) {
        return questionPairCollectionRepository.findById(dto.questionPairCollectionId())
                .map(q -> {
                    if (dto.quizId() != null)
                        q.setQuiz(Quiz.builder().id(dto.quizId()).build());
                    if (dto.title() != null)
                        q.setTitle(dto.title());
                    if (dto.pairs() != null && !dto.pairs().isEmpty())
                        q.setPairs(dto.pairs().stream().map(p -> QuestionPairCollectionPair.builder()
                                .id(p)
                                .build()).toList());
                    return questionPairCollectionRepository.save(q);
                })
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
