package com.zomi.mianshiya.model.vo;

import cn.hutool.json.JSONUtil;
import com.zomi.mianshiya.model.entity.QuestionBankQuestion;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题库题目关系视图
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://www.code-nav.cn">编程导航学习圈</a>
 */
@Data
public class QuestionBankQuestionVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 创建用户信息
     */
    private UserVO user;

    /**
     * 封装类转对象
     *
     * @param questionBankQuestionVO
     * @return
     */
    public static QuestionBankQuestion voToObj(QuestionBankQuestionVO questionBankQuestionVO) {
        if (questionBankQuestionVO == null) {
            return null;
        }
        QuestionBankQuestion questionBankQuestion = new QuestionBankQuestion();
        BeanUtils.copyProperties(questionBankQuestionVO, questionBankQuestion);
        List<String> tagList = questionBankQuestionVO.getTagList();
        questionBankQuestion.setTags(JSONUtil.toJsonStr(tagList));
        return questionBankQuestion;
    }

    /**
     * 对象转封装类
     *
     * @param questionBankQuestion
     * @return
     */
    public static QuestionBankQuestionVO objToVo(QuestionBankQuestion questionBankQuestion) {
        if (questionBankQuestion == null) {
            return null;
        }
        QuestionBankQuestionVO questionBankQuestionVO = new QuestionBankQuestionVO();
        BeanUtils.copyProperties(questionBankQuestion, questionBankQuestionVO);
        questionBankQuestionVO.setTagList(JSONUtil.toList(questionBankQuestion.getTags(), String.class));
        return questionBankQuestionVO;
    }
}
