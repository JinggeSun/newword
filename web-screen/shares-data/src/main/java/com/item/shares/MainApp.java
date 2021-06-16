package com.item.shares;

import com.item.shares.util.HttpUtil;

import java.io.IOException;

public class MainApp {

    private static final String DATA_URL = "https://route.showapi.com/131-53?showapi_appid=549010&showapi_sign=c1c06784aa234e368ef23467aa0b2d1a&page=2";

    public static void main(String[] args) throws IOException {

        String s = HttpUtil.get(DATA_URL);

        System.out.println(s);


        "market": "sh",--市场缩写，比如 SZ 代表深圳交易所
        "name": "博*科",--股票名称
        "currcapital": "5870",--流通股本，万股
        "profit_four": "1.14",--最近四个季度净利润（亿元）
        "code": "60*27",--股票编码
        "totalcapital": "23414.5",--总股本，万股
        "mgjzc": "6.329666",--每股净资产（元）
        "pinyin": "b*k",--股票拼音简写
        "listing_date":"2020-02-02"--上市时间



        https://q.stock.sohu.com/hisHq?code=cn_600009&start=20180716&end=20190720&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp



        Java 获取全部股票信息，历史数据，近期涨幅较大的股票共性分析
                兴趣互联
        爱好互联网，java软件开发工程师，学习python中......
        前言
                获取全部股票信息
        爬取股票的历史信息
                股票分析
        总结
                前言
        随着年龄的增长，及对社会的认知。越来越多的人明白理财的重要性，有的人在 2010 年投资了房地产，获取十倍以上的收益。有的人投资了股票，收益以 2010 年贵州茅台计算收益超过 100 倍。

        我写这篇文章的初衷是获取当前全部股票的信息，收益情况，以及每日的涨幅进行数据的统计，从而帮助我们在众多的股票中排除一下小市值，风险较大的股票。结合我们的 K 线组合，分析符合我们要求的股票。

        获取全部股票信息
        首先介绍一款免费获取股票信息网——万维易源。这里我们直接注册即可使用。

        股票行情接口

        分析接口文档的请求实例和返回值，创建对应的表，存储股票信息。

        接口返回值：

        {
            "showapi_res_code": 0,
                "showapi_res_error": "",
                "showapi_res_id":"ce135f6739**21b76b6fbff",
                "showapi_res_body": {
            "allPages": 25,--总页数
            "contentlist": [
            {
                "market": "sh",--市场缩写，比如 SZ 代表深圳交易所
                "name": "博*科",--股票名称
                "currcapital": "5870",--流通股本，万股
                "profit_four": "1.14",--最近四个季度净利润（亿元）
                "code": "60*27",--股票编码
                "totalcapital": "23414.5",--总股本，万股
                "mgjzc": "6.329666",--每股净资产（元）
                "pinyin": "b*k",--股票拼音简写
                "listing_date":"2020-02-02"--上市时间
            }
        ],
            "currentPage": 1,--当前页
            "allNum": 1217,总数量
            "maxResult": 50--最大返回结果。
        }
        }
        根据接口返回值创建表：

        CREATE TABLE `gp_info` (
  `id` bigint(60) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '股票编码',
  `name` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '股票名称',
  `market` varchar(500) DEFAULT NULL COMMENT '市场缩写，比如 SZ 代表深圳交易所',
  `totalcapital` varchar(500) DEFAULT NULL COMMENT '总股本，万股',
  `currcapital` varchar(500) DEFAULT NULL COMMENT '流通股本，万股',
  `profit_four` varchar(500) DEFAULT NULL COMMENT '最近四个季度净利润（亿元）',
  `mgjzc` varchar(500) DEFAULT NULL COMMENT '每股净资产（元）',
  `state` varchar(500) DEFAULT NULL COMMENT '1 为上市，其他为停牌',
  `listing_date` varchar(500) DEFAULT NULL COMMENT '上市日期',
                PRIMARY KEY (`id`),
                UNIQUE KEY `唯一` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4296 DEFAULT CHARSET=latin1;
        根据表，在 IDEA 中使用 EasyCode 插件，生成对应的 entity、dao、mapper、service 层代码。这里主要是对返回值的处理，直接贴代码及注释。

        根据返回值封装实体 ApiResBody，用来完整地接收接口的返回值，并且根据返回值可以循环获取全部股票信息。

/**
 - 使用实体接收接口返回的全部数据。
 **/
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class ApiResBody implements Serializable {
            private static final long serialVersionUID = -80681498231409067L;
            @JSONField(name = "allNum")
            private Integer allNum;
            @JSONField(name = "currentPage")
            private Integer currentPage;
            @JSONField(name = "allPages")
            private Integer allPages;
            @JSONField(name = "maxResult")
            private Integer maxResult;
            @JSONField(name = "contentlist")
            private List<GpInfo> contentlist;
            @JSONField(name = "ret_code")
            private String retCode;
        }
        由于接口一次只能返回 50 条数据，我们需要分页获取。这是单次获取返回。注意：要下载接口相关的 jar 包，以及配置 appid 及 sign。

        /**
         * 根据页码和类型获取股票信息
         *  currentPage 当前页码
         *  type sh-上海 sz-深圳
         * @return
         */
        public ApiResBody getGpInfoByApi(int currentPage,String type) {
            ApiResBody apiResBody = null;
            try {
                //根据接口文档的 jar 包方法获取股票信息
                String str = new ShowApiRequest("http://route.showapi.com/131-53","***","***")
                        .addTextPara("market",type)
                        .addTextPara("page",String.valueOf(currentPage))
                        .post();
                //将 jsonString 对象解析
                Map parse = (Map) JSON.parse(str);
                //获取股票的信息，并转为 json 对象，方便我们转为实体对象。这里使用的 jackson 包。
                JSONObject showapi_res_body = (JSONObject) parse.get("showapi_res_body");
                ObjectMapper objectMapper = new ObjectMapper();
                apiResBody = objectMapper.readValue(showapi_res_body.toJSONString(), ApiResBody.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //至此，我们将接口的数据完整的转换为我们的实体对象。
            return apiResBody;
        }
        分页获取并保存股票信息：

        /**
         * 保存全部的股票信息
         * @return
         */
        @RequestMapping("saveAllGpInfo")
        @ResponseBody
        public String saveAllGpInfo(@Param("type") String type) throws IOException, InterruptedException {
            if(StringUtils.isEmpty(type)){
                return "parm is empty";
            }
            //第一次获取
            ApiResBody apiResBody = getGpInfoByApi(1,type);
            //查询总页数
            Integer allPages = apiResBody.getAllPages();
            //创建集合存放获取的数据
            List<GpInfo> gpInfoList = new ArrayList<>();
            List<GpInfo> contentlist = apiResBody.getContentlist();
            gpInfoList.addAll(contentlist);
            //循环遍历全部数据，逐页获取
            for (int i = 2;i<=allPages;i++){
                ApiResBody flag = getGpInfoByApi(i,type);
                logger.info("===获取股票信息：[{}]=======",JSON.toJSONString(apiResBody));
                gpInfoList.addAll(flag.getContentlist());
                logger.info("=========接口限制 6 秒访问一次，让线程先睡一会！！！==============");
                Thread.sleep(10000);
            }
            logger.info("=========保存开始！！！==============");
            if(!CollectionUtils.isEmpty(gpInfoList)){
                for(GpInfo gpInfo:gpInfoList){
                    gpInfoService.saveOrUpdate(gpInfo);
                    logger.info("=========保存：[{}]=",JSON.toJSONString(gpInfo));
                }
            }
            logger.info("=========保存完成！！！==============");
            return "ok";
        }
        以上代码直接复制，可以直接获取全部的股票信息。

        爬取股票的历史信息
        首先介绍一款免费获取历史股票行情的的 API 接口——搜狐 API 接口，大家可以查询相关的文档。

        我们分析入参：cn_编码、start 开始时间、end 结束时间、period 天等参数。

        例子：

        https://q.stock.sohu.com/hisHq?code=cn_600009&start=20180716&end=20190720&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp

        获取股票编码为 600009，开始时间 2018 年 7 月 16 到 2019 年 7 月 20 日的走势。分析入参和返回值。

        创建 SQL 存储数据：

        CREATE TABLE `sh_gp` (
  `id` bigint(60) NOT NULL AUTO_INCREMENT COMMENT '搜狐-获取历史数据',
  `code` varchar(50) DEFAULT NULL,
  `kpjg` varchar(50) DEFAULT NULL COMMENT '开盘',
  `spjg` varchar(50) DEFAULT NULL COMMENT '收盘',
  `zde` varchar(50) DEFAULT NULL COMMENT '涨跌额',
  `zf` varchar(50) DEFAULT NULL COMMENT '涨幅',
  `zd` varchar(50) DEFAULT NULL COMMENT '最低',
  `zg` varchar(50) DEFAULT NULL COMMENT '最高',
  `cjl` varchar(50) DEFAULT NULL COMMENT '成交量',
  `cje` varchar(50) DEFAULT NULL COMMENT '成交额',
  `hs` varchar(50) DEFAULT NULL COMMENT '换手',
  `date` varchar(50) DEFAULT NULL COMMENT '日期',
                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1110498 DEFAULT CHARSET=latin1;
    }

}
