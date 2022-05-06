package com.ms.blog.pojo.vo;

import lombok.Data;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:43
 */
@Data
public class ArticleVo {
    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

//    private ArticleBodyVo body;
//
//    private List<TagVo> tags;
//
//    private List<CategoryVo> categorys;

}
