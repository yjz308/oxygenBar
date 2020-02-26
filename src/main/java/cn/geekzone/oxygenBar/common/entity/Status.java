package cn.geekzone.oxygenBar.common.entity;

/**
 * 状态码
 * @author Sunny  An
 *
 */
public enum Status {
    
    /* 基础 */
    
    /** 空 */
    none(0),
    
    /** 成功 */
    success(200),
    
    /** 参数错误 */
    base_param_err(10001),
    
    /** 无效id */
    base_id_err(10002),
    
    /** count个数不正确 */
    base_count_err(10003),
    
    /** 手机号格式不正确 */
    base_phone_fmt_err(10004),
    
    /** 需要登陆 */
    base_need_login(10005), 
    
    /** 上传文件路径不正确 */
    base_file_path_err(10006),
    
    /** 无此用户 */
    base_no_user(10007), 
    
    /** 服务端异常 */
    base_server_exception(10008), 
    
    /** 保存数据失败 */
    base_save_fail(10009), 
    
    /** 删除数据失败 */
    base_del_fail(10010), 
    
    /** 更新数据失败 */
    base_upd_fail(10011), 
    
    /** 未找到记录 */
    base_not_fount(10012),
    
    
    
    
    
    /* 活动、培训相关 */
    
    /**活动-无效活动类型 */
    act_training_type_err(11001), 
    
    /**活动-不可报名 */
    act_can_not_join(11002), 
    
    /**活动-有待付款订单 */
    act_have_un_pay_order(11003), 
    
    /**活动-已参加过 */
    act_already_joined(11004),
    
    /**活动-保存订单失败 */
    act_save_order_fail(11005),
    
    /**活动-保存参加活动状态失败 */
    act_save_join_fail(11006),
    
    /**活动-报名人数已满 */
    act_full(11007),
    
    /**活动-报名人数增加失败 */
    act_add_join_amount_fail(11008),
    
    
    
    
    
    
    
    
    
    
    
    
    
    /* 登录注册相关 */
    
    /** 密码格式不正确 */
    auth_pwd_fmt_err(12001),
    
    /** 居住地不正确 */
    auth_loc_err(12002),
    
    /** 用户注册身份不正确 */
    auth_user_type_err(12003),
    
    /** 手机号已注册 */
    auth_phone_used(12004),

    /** 真实姓名不正确 */
    auth_real_name_err(12005),

    /** 性别不正确*/
    auth_gender_err(12006),

    /** 学历不正确 */
    auth_edu_err(12007),

    /** 公司或商家名称不正确 */
    auth_biz_name_err(12008),
    
    /** 企业类别不正确 */
    auth_biz_type_err(12009),

    /** 手机号已注册 */
    auth_biz_name_used(12010),

    /** 商家注册码不正确 */
    auth_biz_code_err(12011),

    /** 注册失败 */
    auth_reg_fail(12012),
    
    /** 登录失败，账号密码有误 */
    auth_login_fail(12013),

    /** 更新token失败 */
    auth_upd_token_fail(12014),

    /** token 不正确 */
    auth_token_err(12015),

    /** token 已过期 */
    auth_token_expired(12016),

    /** user id 不正确 */
    auth_user_id_err(12017),

    /** 修改密码失败 */
    auth_change_pwd_fail(12018),

    /** 昵称格式不正确 */
    auth_nick_fmt_err(12019),

    /** 邮箱不正确 */
    auth_email_fmt_err(12020),

    /** 更新资料失败 */
    auth_upd_user_info_fail(12021),

    /** 修改密码失败 */
    auth_upd_pwd_err(12022),
    
    /** QQ授权失败 */
    auth_qq_fail(12023),
    
    /** 未绑定该QQ账号 */
    auth_no_qq_user(12024),
    
    /** 该QQ账号已被绑定 */
    auth_qq_user_used(12025),
    
    /** 已经绑定QQ账号 */
    auth_already_have_qq(12026),
    
    /** 微信授权失败 */
    auth_weixin_fail(12027),
    
    /**用户名不存在*/
    auth_username_not(12028),
    
    /**密码错误*/
    auth_password_error(12029),
    
    /**已经绑定微信*/
    auth_aleady_wx_bind(12030),
    
    /**已经绑定qq*/
    auth_aleady_qq_bind(12031),
    
    
    

    
    
    
    
    
    
    
    /* 天气相关 */
    weather_get_fail(13001), 
    
    
    
    
    
    
    /* 社区相关 */
    
    /** 评论类型不正确 */
    comm_reply_type_err(14001), 
    
    /** 评论内容不正确 */
    comm_reply_content_err(14002), 
    
    /** 帖子id型不正确 */
    comm_post_id_err(14003), 
    
    /** 保存评论失败 */
    comm_save_reply_fail(14004), 
    
    /** 评论id不正确 */
    comm_reply_id_err(14005), 
    
    /** 子标题不正确 */
    comm_title_err(14006), 
    
    /** 评论类型不正确 */
    comm_post_content_err(14007), 
    
    /** 保存帖子失败 */
    comm_save_post_fail(14008), 
    

    
    
    
    
    
    
    /* 文化资讯，通知，博物，创意 */
    
    /** 资讯类型不正确 */
    news_type_err(15001),
    
    
    
    
    
    
    
    /* 商品相关 */
    
    /** 商品一级类别不正确 */
    goods_first_type_err(16001),
    
    /** 商城id不正确 */
    goods_online_shop_id_err(16002),
    
    /** 商品id不正确 */
    goods_id_err(16003),
    
    
    
    
    
    /* 验证码相关 */
    
    /** 验证码格式不正确 */
    phone_code_fmt_err(17001),
    
    /** 业务代码不正确 */
    phone_code_operation_err(17002),
    
    /** 验证码格式不正确 */
    phone_code_expired(17003),
    
    /** 验证码不正确 */
    phone_code_err(17004),
    
    /** 验证码格式不正确 */
    phone_code_unknown_err(17005),
    
    /** 验证码格式不正确 */
    phone_code_send_fast(17006),
    
    /** 验证码格式不正确 */
    phone_code_send_fail(17007),
    
    
    
    
    
    
    
    
    /* 上传文件相关 */
    
    /** 保存上传图片记录失败 */
    upload_save_fail(18001), 
    
    /** 无效的文件 */
    upload_no_file(18002), 
    
    /** 无效的文件类型 */
    upload_file_type_err(18003), 
    
    
    
    
    
    
    
    /* 商品规格相关 */
    
    /** 商品规格id不正确 */
    speci_id_err(19001), 
    
    
    
    
    
    
    /* 购买相关 */
    
    /** 购买数量不正确 */
    buy_amount_err(21001), 
    
    /** 用户积分低于商品积分门槛 */
    buy_point_request_high(21002),
    
    /** 库存不足 */
    buy_no_goods(21003),
    
    /** 超过最大购买量 */
    buy_too_much(21004), 
    
    /** 不是虚拟产品 */
    buy_not_virtual_goods(21005), 
    
    /** 积分不足 */
    buy_no_sufficient_point(21006),
    
    /** 减去用户积分失败 */
    buy_use_point_fail(21007),
    
    /** 增加用户积分失败 */
    buy_add_point_fail(21008),
    
    /** 减库存失败 */
    buy_sub_stock_fail(21009),
    
    /** 保存订单失败 */
    buy_save_order_fail(21010),
    
    /** 商品与规格不符 */
    buy_no_match_goods_speci(21011),
    
    /** 保存订单详情失败 */
    buy_save_order_detail_fail(21012),
    
    /** 下单异常 */
    buy_order_exception(21013),
    
    /** 保存商家兑换码失败 */
    buy_save_code_fail(21014), 
    
    /** 不是现金商品 */
    buy_not_cash_goods(21015), 
    
    /** 虚拟商品类型不正确 */
    buy_virtual_type_err(21016), 
    
    /** 无效的收货地址 */
    buy_address_err(21017), 
    
    /** 留言长度超过限制 */
    buy_comment_err(21018), 
    
    
    
    
    
    
    /* 账户相关 */
    
    /** 未找到账户信息 */
    account_not_found(22001), 
    
    
    
    
    
    
    /* 积分相关 */
    
    /** 保存积分记录失败 */
    point_save_fail(23001), 
    
    
    
    
    /* 订单相关 */
    
    /** 无效的交易单号 */
    order_trade_no_err(24001), 
    
    /** 未找到该交易单号 */
    order_trade_no_not_found(24002), 
    
    /** 不是未支付订单*/
    order_not_unpay(24003), 
    
    /** 更新订单失败*/
    order_upd_fail(24004), 
    
    /** 订单不能被删除 */
    order_can_not_del(24005), 
    
    /** 订单删除失败 */
    order_del_fal(24006), 
    
    /** 不是待评价的订单 */
    order_not_no_comment(24007),
    
    /** 设置订单为已评价状态失败 */
    order_set_comment_status_fail(24008),
    
    /** 只有已发货订单才能确认收货 */
    order_confirm_receipt_fail(24009),
    
    /** 订单完成超过90天，无法评价 */
    order_over_time_comment(24010),
    /** 最近15天内评价过相同商品的其他订单,无法评价该订单 */
    order_repeat_comment(24011),
    
    /*	签到相关 */
    /** 今天已签到 */
    aleady_sign_today(25001),
    
    /** 今天没签到**/
    no_sign_today(25002),
    
    
    
    
    ;
    
    private int code;
    
    Status(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }

}
