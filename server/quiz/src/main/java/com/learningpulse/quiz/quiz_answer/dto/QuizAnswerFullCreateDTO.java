package com.learningpulse.quiz.quiz_answer.dto;

import com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_answer.QuestionCheckboxAnswerEmbedCreateDTO;
import com.learningpulse.quiz.question_answer.question_file_answer.dto.question_file_answer.QuestionFileAnswerEmbedCreateDTO;
import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_answer.QuestionOrderAnswerEmbedCreateDTO;
import com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_answer.QuestionPairCollectionAnswerEmbedCreateDTO;
import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_answer.QuestionRadioAnswerEmbedCreateDTO;
import com.learningpulse.quiz.question_answer.question_text_answer.dto.question_text_answer.QuestionTextAnswerEmbedCreateDTO;

import java.util.List;
import java.util.UUID;

public record QuizAnswerFullCreateDTO(
        UUID quizId,
        List<QuestionCheckboxAnswerEmbedCreateDTO> questionCheckboxAnswers,
        List<QuestionFileAnswerEmbedCreateDTO> questionFileAnswers,
        List<QuestionOrderAnswerEmbedCreateDTO> questionOrderAnswers,
        List<QuestionPairCollectionAnswerEmbedCreateDTO> questionPairCollectionAnswers,
        List<QuestionRadioAnswerEmbedCreateDTO> questionRadioAnswers,
        List<QuestionTextAnswerEmbedCreateDTO> questionTextAnswers
) {
}
