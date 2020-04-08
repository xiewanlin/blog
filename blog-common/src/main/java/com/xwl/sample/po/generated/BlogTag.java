package com.xwl.sample.po.generated;

import com.xwl.sample.base.model.BaseModel;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @table blog_tag
 */
@Getter
@Setter
public class BlogTag extends BaseModel {
    /**
     * @column id
     * @type BIGINT(19) @pk @autoIncrease @required
     *   id
     */
    private Long id;

    /**
     * @column name
     * @type VARCHAR(32) @required
     *   标签名称
     */
    private String name;

    /**
     * @column create_time
     * @type TIMESTAMP(19) @required
     *   创建时间
     */
    private Date createTime;

    /**
     * @column create_name
     * @type VARCHAR(32) @required
     *   创建用户
     */
    private String createName;

    /**
     * @column update_time
     * @type TIMESTAMP(19)
     *   更新时间
     */
    private Date updateTime;

    /**
     * @column update_name
     * @type VARCHAR(32)
     *   更新用户
     */
    private String updateName;
}