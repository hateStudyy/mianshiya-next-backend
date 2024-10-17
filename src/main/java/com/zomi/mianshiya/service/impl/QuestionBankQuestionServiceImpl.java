package com.zomi.mianshiya.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zomi.mianshiya.common.ErrorCode;
import com.zomi.mianshiya.exception.ThrowUtils;
import com.zomi.mianshiya.model.entity.Question;
import com.zomi.mianshiya.model.entity.QuestionBank;
import com.zomi.mianshiya.model.entity.QuestionBankQuestion;
import com.zomi.mianshiya.service.QuestionBankQuestionService;
import com.zomi.mianshiya.mapper.QuestionBankQuestionMapper;
import com.zomi.mianshiya.service.QuestionBankService;
import com.zomi.mianshiya.service.QuestionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Administrator
* @description 针对表【question_bank_question(题库题目)】的数据库操作Service实现
* @createDate 2024-10-14 01:04:20
*/
@Service
public class QuestionBankQuestionServiceImpl extends ServiceImpl<QuestionBankQuestionMapper, QuestionBankQuestion>
    implements QuestionBankQuestionService{

    @Resource
    @Lazy
    private QuestionService questionService;

    @Resource
    @Lazy
    private QuestionBankService questionBankService;

    @Override
    public void validQuestionBankQuestion(@NotNull QuestionBankQuestion questionBankQuestion, boolean add) {
        ThrowUtils.throwIf(questionBankQuestion == null, ErrorCode.PARAMS_ERROR);
        // 题目和题库必须存在
        Long questionId = questionBankQuestion.getQuestionId();
        if (questionId != null) {
            Question question = questionService.getById(questionId);
            ThrowUtils.throwIf(question == null, ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        Long questionBankId = questionBankQuestion.getQuestionBankId();
        if (questionBankId != null) {
            QuestionBank questionBank = questionBankService.getById(questionBankId);
            ThrowUtils.throwIf(questionBank == null, ErrorCode.NOT_FOUND_ERROR, "题库不存在");
        }
    }
}




