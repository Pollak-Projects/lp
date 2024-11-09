package com.learningpulse.quiz.quiz.dto;

import com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox.QuestionCheckboxEmbedCreateDTO;
import com.learningpulse.quiz.question.question_file.dto.question_file.QuestionFileEmbedCreateDTO;
import com.learningpulse.quiz.question.question_order.dto.question_order.QuestionOrderEmbedCreateDTO;
import com.learningpulse.quiz.question.question_pair.dto.question_pair_collection.QuestionPairCollectionEmbedCreateDTO;
import com.learningpulse.quiz.question.question_radio.dto.question_radio.QuestionRadioEmbedCreateDTO;
import com.learningpulse.quiz.question.question_text.dto.question_text.QuestionTextEmbedCreateDTO;

import java.time.LocalDateTime;
import java.util.List;

public record QuizFullCreateDTO(
        String name,
        String description,
        LocalDateTime deadline,
        Boolean viewAfterSubmission,
        List<QuestionCheckboxEmbedCreateDTO> questionCheckboxes,
        List<QuestionFileEmbedCreateDTO> questionFiles,
        List<QuestionOrderEmbedCreateDTO> questionOrders,
        List<QuestionPairCollectionEmbedCreateDTO> questionPairCollections,
        List<QuestionRadioEmbedCreateDTO> questionRadios,
        List<QuestionTextEmbedCreateDTO> questionTexts
) {
}
