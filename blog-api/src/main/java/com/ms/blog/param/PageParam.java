package com.ms.blog.param;

import lombok.Data;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:31
 */
@Data
public class PageParam {

    private int page = 1;

    private int pageSize = 10;

}
