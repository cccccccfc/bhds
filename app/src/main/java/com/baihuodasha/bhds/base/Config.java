package com.baihuodasha.bhds.base;

/**
 * author：Anumbrella
 * Date：16/5/23 下午11:45
 */
public final class Config {
    //
    ///**
    // * 引导界面本地图片
    // */
    //public static final int[] pics = { R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};


    /**
     * 引导界面服务器地址上的图片
     */
    public static final String[] pricUrls = {
            "http://www.anumbrella.net/App/AppShopService/Guide/guide1.jpg",
            "http://www.anumbrella.net/App/AppShopService/Guide/guide2.jpg",
            "http://www.anumbrella.net/App/AppShopService/Guide/guide3.jpg",
            "http://www.anumbrella.net/App/AppShopService/Guide/guide4.jpg"
    };
    /**
     * 商品分类左边的目录菜单
     */
    public static final String[] categorizeTools = {
            "热门", "百货超市", "居家生活", "家用电器", "餐具厨具", "3C数码", "智能家居",
    };
    /**
     * 推荐列表快捷导航栏文本
     */
    public static final String[] recommdendListTexts = {
            "全新手机", "手机配件", "保护壳/膜", "手机维修", "联系我们", "找到我们"
    };

    /**
     * 推荐列tip导航栏文本
     */
    public static final String[] recommdendTips = {
            "全新手机", "二手良品", "手机配件", "保护壳/膜"
    };

    /**
     * 手机碎屏可维修手机类型
     */
    public static final String[] repairScreenPhoneTypes = {
            "三星", "iPhone", "魅族", "小米", "华为", "其他"
    };


    /**
     * 手机维修标题
     */
    public static final String[] repairTexts = {
            "碎屏", "无法开机", "进水", "其他"
    };


    /**
     * 手机类型对应数字值
     */
    public static final String[][] productTypeArrays = {
            {"type", "phoneType"},
            {"0", "全新手机"},
            {"1", "二手良品"},
            {"2", "手机配件"},
            {"3", "手机壳/膜"}

    };


    /**
     * 手机运营商对应数字值
     */
    public static final String[][] productCarrieroperatorArrays = {
            {"type", "carrieroperator"},
            {"0", "移动4G"},
            {"1", "联通4G"},
            {"2", "电信4G"},
            {"3", "全网通"}

    };


    /**
     * 手机内存对应数字值
     */
    public static final String[][] productStorageArrays = {
            {"type", "storage"},
            {"0", "16G"},
            {"1", "32G"},
            {"2", "64G"},
            {"3", "128G"}
    };


    /**
     * 手机颜色对应数字值
     */
    public static final String[][] productColorArrays = {
            {"type", "color"},
            {"0", "深空灰"},
            {"1", "银色"},
            {"2", "香槟金"},
            {"3", "玫瑰金"},
            {"4", "黑色"},
            {"5", "白色"}

    };

    public static final String[] numberText = {
            "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩", "⑪", "⑫", "⑬", "⑭", "⑮", "⑯"
    };








}
