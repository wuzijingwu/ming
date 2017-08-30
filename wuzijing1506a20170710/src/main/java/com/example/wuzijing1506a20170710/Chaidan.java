package com.example.wuzijing1506a20170710;

import java.util.List;

/**
 * Created by dell on 2017/7/10.
 */

public class Chaidan {


    /**
     * 姓名：吴子敬
     * 时间：2017.07.10 *
     * 作用：封装一个类
     *
     *
     */
    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * data : [{"id":"92","title":"红烧肉","tags":"家常菜;快手菜","imtro":"红烧肉，各地各家的做法稍有不同味道即大不一样，中餐的精妙在于食材随意性而产生的变化，南方习惯用酱油(老抽)调色，而北方则偏爱炒糖色。 女人不要为了保持好身材，刻意与美味的肉类食品绝缘哦。其实，很多科学证明，适当的吃肉并不会增加额外的脂肪。 猪肉经过小火煸炒出油后，炖出来的红炒肉是肥而不腻，软烂入味，非常的好吃美味，也非常适合咱们的健康理念，即少油，又解馋，又解腻，下面，为你推荐这种红烧肉的经典做法，不用一滴油就可以做出美味健康的红烧肉。","ingredients":"猪后臀尖,1000g","burden":"葱段,适量;八角,2个;干辣椒,4个;香叶,4片;桂皮,1块;鲜姜,1块;干山楂片,4片;黄油,适量;老抽,适量;生抽,适量;白糖,适量;开水,适量","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/92_512827.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_472370e29b980b31.jpg","step":"1.猪肉清洗干净切成方块，冷水下锅，水开后撇去上面的浮沫。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_04f90c14513aa139.jpg","step":"2.焯好的肉块捞出，用温水清洗干净。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_50af1fae3911794d.jpg","step":"3.准备好调料：干辣椒，桂皮，香叶，八角，（大蒜也可以不放）。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_12f839ee0920c03b.jpg","step":"4.做锅开小火，不放油，把肉块放入反复煸炒，煸炒至肉块有点焦香，有油渗出，关火把肉块捞出。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_bfdc35cde7da929b.jpg","step":"5.做锅，用肉块煸出的猪油烧热，放入八角，干辣椒，桂皮，香叶煸炒出香味。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_e21dddc1ffcad36b.jpg","step":"6.放入肉块煸炒1-2分钟后捞出备用。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_89ba401801a762fe.jpg","step":"7.用锅中剩下的余油放入2勺白糖（这个量可以根据自己的喜好添加，喜欢吃甜一点的就多放点糖），开小火煸炒白糖。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_33f84ea85dcd11c9.jpg","step":"8.这是糖色变化的过程"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_bbf69240b5f7211d.jpg","step":"9.当糖起小泡，颜色变红就可以了。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_114bbdd5ef3d1255.jpg","step":"10.这时快速烹上1勺热水，加入肉块煸炒至肉块颜色变红。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_831b33673ac8c507.jpg","step":"11.煸炒至猪肉块上色后，加入适量生抽，老抽（不可多放，放多了颜色就发黑了），黄酒煸炒。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_a377c03180cb6b26.jpg","step":"12.加入开水（多放点水，最好一次加足，肉炖熟了可以大火收汤，水少了，中途加水就影响肉质的口感了），再把姜块，葱段，山楂片放入。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_127b81298ad6cf93.jpg","step":"13.盖上锅盖，大火烧开，小火炖煮1个小时。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_59ebcf1a60e54619.jpg","step":"14.炖煮了一个小时了，汤汁也差不多剩一半了，这时加入6克盐（盐可以根据自己的口味添加，在还有汤的情况下，不要尝出咸味正好，等汤汁收浓就会咸了），小火继续炖煮。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_f4cbb0c04220428e.jpg","step":"15.当品尝到肉质软烂，就可以大火收汁，一锅肥而不腻，肉质酥烂，诱人食欲的红烧肉就炖好了，赶快就着小酒，大快朵颐的享受吧"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_372112ff28389c98.jpg","step":"16.炎热的夏季，吃一顿少油，解腻，又美味的红烧肉大餐。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_1630281ff0350105.jpg","step":"17.再来一张，想吃吗，那就赶紧动手吧，呵呵。"}]},{"id":"143","title":"红烧肉","tags":"家常菜;炒;红烧;炒锅","imtro":"对于红烧肉，大家都不陌生，想吃却又矛盾着，其实肉类总吃固然不好，但是平时多吃些油脂少的菜，偶尔吃一次红烧肉，对于健康是没有影响滴。现在这个季节天气渐渐变冷，做出一锅红烧肉，用来炖菜，做卤肉饭，都让人很难拒绝。其实红烧肉只要糖色熬好了，把油煸炒出去之后，离成功就不远了，今天我用了红糖熬的糖色，发现做出来的颜色更漂亮，口感也很好！","ingredients":"带皮五花肉,400g","burden":"食用油,少量;盐,少量;味精,适量;料酒,适量;姜汁,数滴;生抽,适量;老抽,适量;红糖,1勺;肉蔻,2个;桂皮,1根;川椒,2个;香叶,3片;八角,3个","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/143_372552.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/2/143_ed285de97eaf20b7.jpg","step":"1.带皮五花肉洗干净放人沸水锅中汆水片刻捞出（这样便于切块）"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/2/143_624b8a73be180c44.jpg","step":"2.切成大小适中的块状，再次放入沸水锅中汆去血水，捞出沥干水分"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/2/143_82e48f7919d91477.jpg","step":"3.锅中少油，放入红糖、生抽、老抽中火熬到糖起泡"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/2/143_3afc0ce046df186f.jpg","step":"4.放入葱段，倒入姜汁和料酒煸炒出香味"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/2/143_b0c9b3709bd47298.jpg","step":"5.倒入五花肉翻炒均匀至每块肉都上色，继续煸炒，要让五花肉的油脂煸出，将煸出的油倒出"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/2/143_7be96162740d3818.jpg","step":"6.锅中填入适量清水，将调料装入调料包放入锅中，香叶、桂皮也一起放入，撒少许盐大火烧沸，转小火烧1个半小时，待肉烧透汤汁浓稠，撒入味精出锅即可"}]},{"id":"220","title":"红烧肉","tags":"家常菜;半小时-1小时;咸鲜;红烧;1-2人;炒锅;体力劳动者","imtro":"提起红烧肉，我们自然不能忘记那位将吃红烧肉事业推向高峰的人－－苏东坡。 　　 正是由于他的努力，红烧肉才得以从基层走向了上层，从老百姓的菜锅走上了文人墨客的餐桌。 红烧肉是热菜菜谱之一，以五花肉为制作主料，红烧肉的烹饪技巧以砂锅为主，口味属于甜味。红烧肉是一道著名的本帮菜，充分体现了本帮菜\u201c浓油赤酱\u201d的特点。 第一次做，感觉还好，就是不怎么好看。","ingredients":"红烧肉,300g","burden":"油盐,适量;老抽,适量;料酒,适量;白糖,适量","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/220_839244.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/220_3d569986929bf711.jpg","step":"1.把准备好的肉放入沸水中稍煮片刻，（肥肉变得将近透明）捞出。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/220_c33def95ff008168.jpg","step":"2.起锅热油，放肉加入老抽翻炒直到每块肉都均匀的上色为止，"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/220_d00b2100cdeadf8d.jpg","step":"3.加热水，料酒，白糖等开中火煮，直到剩余少量汤汁。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/220_8a63174d6b5599d4.jpg","step":"4.装盘前可以试试味，然后依照个人口味加料。"}]},{"id":"231","title":"红烧肉","tags":"红烧;午餐;增强抵抗力;家常菜;白领","imtro":"昨晚下了一场小雨，早上起来就感觉冷了许多。天气一冷，人的食欲就大增，就想大口吃肉，这时候就把红烧肉做起来吧！","ingredients":"五花肉,250g","burden":"生姜,适量;秘制红烧汁,20g;盐,适量","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/231_225409.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_941767931a3a7884.jpg","step":"1.准备好材料"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_d613154848dd5b64.jpg","step":"2.五花肉切小块，烧一锅水，水开后放入切好的五花肉和3片姜一起飞水。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_99154f2d7cf20cf1.jpg","step":"3.煮至五花肉断生后，捞起待用。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_22ebf25569f2c62f.jpg","step":"4.锅子烧热，放少许油，并放入剩下的姜片爆香。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_1fb50017ca11938c.jpg","step":"5.倒入五花肉，"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_10e806511d3a3acd.jpg","step":"6.烧至微黄。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_5eb47ab44ee04ce8.jpg","step":"7.倒入一碗开水，"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_d3e174b1385e8f57.jpg","step":"8.倒入适量的红烧汁，"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_400e6399f4ae5362.jpg","step":"9.放入一小勺盐，试一下汤汁的味道，这时候的味道应该稍微淡一点，收汁后味道会变得重一下。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_ae026dd51e6f0d5a.jpg","step":"10.盖上锅盖小火焖45分钟左右，软烂即可。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/231_c4ecf0fe7980d2bf.jpg","step":"11.大火收汁，装碟即可。"}]},{"id":"278","title":"土豆红烧肉","tags":"家常菜;红烧;增强抵抗力;宴客菜","imtro":"不用炒糖色也可打造极品红烧肉： 一、五花肉焯水时一定要凉水下锅，对去除五花肉中多余的油脂有很好的效果，而且在水中加一勺黄酒还能去除肉的腥味 二、不会炒糖色，可用番茄酱代替，不但颜色漂亮而且其中的酸也会解五花肉的油腻。 三、糖与肉的比例，500克肉放8\u201410克冰糖，属于中等红色。加一勺老抽，属于大红色。 四、调料用八角、桂皮、香叶就足以增加猪肉的香味了，用香料过多反而会掩盖肉的香味。 五、文火肉，急火鱼，大火烧开，转小火慢炖，当接近酥烂时，要立即转入大火收浓汤汁，收汁不要过紧，过紧汤汁浓稠，会失去红烧菜的特色。","ingredients":"带皮五花肉,650g;大土豆,1个","burden":"油,适量;盐,5g;葱,1段;姜,3片;八角,1个;草果,1个;香叶,1片;小茴香,2g;花椒,2g;冰糖,10g;番茄酱,20g;黄酒,10g;生抽,15;老抽,10g","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/278_459533.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_57794b2ee30f28aa.jpg","step":"1.原料"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_3f625e352547db9b.jpg","step":"2.辅料"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_cf0aceca352001c8.jpg","step":"3.五花肉洗净切块，土豆去皮洗净，切小块"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_aa796c1cbd5b6022.jpg","step":"4.五花肉冷水下锅，加一勺黄酒，几粒花椒，焯水"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_6fcf071e2334891b.jpg","step":"5.热锅冷油，放番茄酱炒出红油"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_167fbeee49ac4dc0.jpg","step":"6.倒入焯好的五花肉，小火煸炒至五花肉上色"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_eff23c7fd6cc9cd2.jpg","step":"7.调入黄酒、盐，生抽，老抽"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_48a29d6b00b0ccd1.jpg","step":"8.加入冰糖"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_f6bcd83eb3b3155f.jpg","step":"9.转移至砂锅中，加开水，没过五花肉"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_750d779f27e38aeb.jpg","step":"10.放入葱、姜，调料放入调料盒中，放入锅中，大火烧开，转小火盖上盖子慢炖一小时左右"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_1646f1cfe461d5d6.jpg","step":"11.倒入土豆，烧到土豆软烂"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/3/278_3ce0fea21531d2c5.jpg","step":"12.然后，再转大火收汁即可"}]},{"id":"325","title":"红烧肉炖土豆","tags":"家常菜;炖;冬季;增强抵抗力;健脾养胃;贴秋膘","imtro":"红烧肉炖土豆是一道人气很高的家常菜。因为土豆营养价值很高，加入在美味诱人的红烧肉中，能够吸走肉的油脂，可以减少肉的油腻感，而土豆浸透了浓郁的肉香，更加入味，绵绵糯糯的非常好吃。我家喜欢吃瘦一点的肉，五花肉有点肥，所以猪肉我选用了猪前尖肉，这样炖出的红烧肉瘦肉比较多但是不会柴。 猪肉类含有丰富的蛋白质、脂肪、微量元素等人体健康必需的物质，而且口感香、鲜，是国人餐桌传统必备的美食，也是养生佳品。吃肉不仅是为了调节口味，还能营养身体。 土豆的营养价值非常高，可预防中风、和胃健脾。土豆对人体有很奇妙的作用，瘦人食之能使其\u201c胖\u201d，胖人食之则使其\u201c瘦\u201d，且身段会变得苗条起来。不必受节食之苦便能收到\u201c越贪吃越美丽\u201d的效果。","ingredients":"猪前肩肉,500g;土豆,200g","burden":"油,适量;盐,适量;冰糖,20g;红烧酱油,适量;葱,适量;姜,适量;八角,2粒;桂皮,适量;料酒,适量","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/325_442125.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_fe7bae4376463de3.jpg","step":"1.准备好土豆和猪前肩肉。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_108c26212e9121b8.jpg","step":"2.猪肉洗净切成块，用清水泡1小时，泡出血水。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_267e2db41161a770.jpg","step":"3.葱切段、姜切片。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_62ce8b5abdd70689.jpg","step":"4.土豆去皮切成滚刀块。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_01b332585f1bc764.jpg","step":"5.将锅置于火上，倒入食用油，热后下入土豆块。 炸成黄色，捞出控净油。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_99eea44be96f05f4.jpg","step":"6.把肉水焯一下，捞出备用。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_d412e8b8dbc7b9fc.jpg","step":"7.锅中加入少量的油，下入焯好水的肉煸炒至微黄铲出。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_22614699dc083164.jpg","step":"8.锅中留底油，下入冰糖。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_5accf4b71c9d25f7.jpg","step":"9.小火炒至冰糖融化成棕红色。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_4009d44173b951c2.jpg","step":"10.倒入煸炒的肉块，继续煸炒，让每一块肉都能够裹上糖色。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_503bde9d26192c5a.jpg","step":"11.加入酱油煸炒均匀。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_9902412da86c390f.jpg","step":"12.烹入料酒。炒拌均匀。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_6ed17be846023de0.jpg","step":"13.加入开水，没过肉。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_a380613dfe84e029.jpg","step":"14.烧开后加入葱姜 八角 桂皮。转小火焖至30分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_6755eb4f86d06e5b.jpg","step":"15.加入盐和煎炸好的土豆，再炖焖30分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/325_7c2248dcede931ab.jpg","step":"16.转中火，烧至汤汁浓缩即可。"}]},{"id":"350","title":"家常红烧肉","tags":"家常菜;热菜;增强抵抗力;秋季菜谱;贴秋膘","imtro":"周末去超市大采购，正好碰上打折的上好五花，才8元多一斤。 仔仔细细挑了一块，美美的回家做了这道红烧肉。为了让肉炖的更软糯，我用的是高压锅，肉香味美，还省了不少时间呢。以前妈妈一直都是用的炒锅来做红烧肉的，我给改良了，深得家人的认可。当然，高压锅来制作有几个小技巧需要注意下。我用这个方法烧过牛肉，味道也超赞。","ingredients":"五花肉,700g","burden":"冰糖,适量;盐,适量;老抽,适量;蒜,适量","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/350_693533.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/350_d0d0051db44d5134.jpg","step":"1.五花肉洗净放入凉水中煮开汆烫至变色即可。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/350_27936c3a5bf3853a.jpg","step":"2.将汆烫变色的五花肉捞起冲洗干净。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/350_b69103cb08b427ea.jpg","step":"3.将五花肉切成小四方块放入高压锅，加入没过肉一指的凉水。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/350_0ebed53e66beeae0.jpg","step":"4.往锅中倒入老抽适量，为了使肉上色。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/350_e2d077372cf7d960.jpg","step":"5.往锅里加入适量的盐。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/350_189e12ccb31250b9.jpg","step":"6.将高压锅盖上，开大火，高压锅气压上足发出声音后，转中小火炖15分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/350_49d0530978e4b4b7.jpg","step":"7.炖熟的肉里面加入大蒜。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/350_c86a4fe9fac4f86b.jpg","step":"8.加入一颗冰糖，小火将收干汤汁即可享用。"}]},{"id":"375","title":"红烧肉烧腐竹","tags":"家常菜;老年人;红烧;增强抵抗力;鲜香;健脾养胃;贴秋膘;防止高脂血症","imtro":"醇香谗人，色泽红亮的红烧肉烧腐竹是我非常喜欢的一道营养搭配均衡的下饭菜，腐竹与肉一同烧制味道非常完美。腐竹加入在美味诱人的红烧肉中，能够吸走肉的油脂，可以减少肉的油腻感，而腐竹浸透了浓郁的肉香，更加入味，我家喜欢吃瘦一点的肉，五花肉有点肥，所以猪肉我选用了猪前尖肉，这样炖出的红烧肉瘦肉比较多但是不会柴。且肥而不腻、酥而不碎。 腐竹，又称腐皮或豆腐皮，是煮沸豆浆表面凝固的薄膜。腐竹是中国人很喜爱的一种传统食品，具有浓郁的豆香味，同时还有着其他豆制品所不具备的独特口感。腐竹色泽黄白，油光透亮，含有丰富的蛋白质及多种营养成分。腐竹具有良好的健脑作用，它能预防老年痴呆症的发生。腐竹中所含有的磷脂还能降低血液中胆固醇含量，有防止高脂血症、动脉硬化的效果。","ingredients":"猪肉,500g;腐竹,100g","burden":"油,适量;盐,适量;葱,适量;姜,适量;八角,适量;辣椒,适量;香叶,适量;桂皮,适量;冰糖,适量;红烧酱油,适量","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/375_723350.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_1bd75af57dbf4d63.jpg","step":"1.把猪肉洗净切成块，放入清水中泡至1小时，泡出血水。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_274d904566f13f6c.jpg","step":"2.准备好八角 桂皮 香叶 辣椒。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_14a228c6b77266b8.jpg","step":"3. 葱切段、姜切片。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_97ea0902551ad522.jpg","step":"4.腐竹提前泡发。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_2e7e5ad52171e3ca.jpg","step":"5.把肉水焯一下，捞出备用。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_e74d411a5f20b52a.jpg","step":"6.锅中加入少量的油，下入焯好水的肉煸炒至微黄铲出。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_691689bab668a8ae.jpg","step":"7.锅中留底油，下入冰糖。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_3ddfcbcc869286ba.jpg","step":"8.小火炒至冰糖融化成棕红色。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_bed98eca949972fb.jpg","step":"9. 倒入煸炒的肉块，继续煸炒，让每一块肉都能够裹上糖色。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_e25c0bdf2650923b.jpg","step":"10.加入酱油煸炒均匀。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_743d92a8ee763c5a.jpg","step":"11.烹入料酒。炒拌均匀。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_95473e2601443037.jpg","step":"12. 加入开水，没过肉。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_a7d0ceb4a44cf975.jpg","step":"13. 烧开后加入葱姜 八角 桂皮 香叶 辣椒。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_5e9eea821bfc8744.jpg","step":"14.转小火焖至30分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_d1289fc09dfe505a.jpg","step":"15.加入盐和泡发好的腐竹，再炖焖30分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/4/375_640f832e6aa6a386.jpg","step":"16.转中火，烧至汤汁浓缩即可。"}]},{"id":"468","title":"红烧肉","tags":"家常菜;老年人;咸;青少年;白领;红烧;1-2人;炒锅;1小时-2小时","imtro":"一直觉得大口喝酒大块吃肉，是作为人最豪迈的事情，好久没这么畅快的吃肉了，物价高，穷啊，只好不吃了，这不要过中秋节了吗，就是旧社会，也得想办法改善一下生活，俺也拼点银子，上街买块肉，回家煮给娃吃。于是呢，便有了今天的这道红烧肉。 红烧肉应当是一道全国人民都热爱的经典菜，各家有各家演绎的方法，虽然演绎方法不同，但却有相同的结果，那就是各家幸福和快乐的味道。","ingredients":"五花肉,500g","burden":"葱,适量;姜,适量;食盐,适量;食用油,适量;甜面酱,适量;鲜花椒,适量;大茴,适量;草果,适量;豆蔻,适量;桂皮,适量;香叶,适量;丁香,适量;白芷,适量;香沙,适量","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/468_696872.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_02e6163c5b914835.jpg","step":"1.五花肉洗净放在锅里，加入葱姜和料酒。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_d05e8cc2ed923d00.jpg","step":"2.放在火上，煮15-20分钟左右。中途会有浮沫飘出，可以打开锅盖去掉。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_055d17ae30395b93.jpg","step":"3.煮好的肉捞出晾凉备用。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_b4121227327513fa.jpg","step":"4.将晾凉的五花肉切成大块备用。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_e71b67d627e2ca11.jpg","step":"5.炒锅放入食用油，加入白糖炒糖汁。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_fa76b5406cafc32c.jpg","step":"6.糖炒化后就可以了，注意不要炒的太老，影响口感。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_18e901d6a2a678d4.jpg","step":"7.下入切好的五花肉块翻炒。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_10a811b8f5d36c03.jpg","step":"8.让五花肉均匀粘上糖汁上色。等上糖色后，可以加少许老抽翻炒，使艳色更红艳。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_4c5b00dce98fa23b.jpg","step":"9.加入葱姜翻炒根据需要加入食盐。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_0a16a2eb5ce0bfd1.jpg","step":"10.最后放入一些甜面酱。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_2a1fd9353acc1db9.jpg","step":"11.鲜花椒洗净备用。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_b4616b4609b1b209.jpg","step":"12.香料放入清水里洗去灰尘。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_ec513101b05a07e0.jpg","step":"13.锅内加入清水，放入洗好的香料。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_50143f7a3aba6435.jpg","step":"14.将炒锅内的食材，移到电压力煲中。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/468_146ffd1a62acad69.jpg","step":"15.直接选定肉类排骨，烹饪过程结束，排气后即可食用。"}]},{"id":"481","title":"红烧肉炖山药","tags":"家常菜;老年人;咸;半小时-1小时;炖;青少年;白领;秋季;1-2人;炒锅;雨水","imtro":"冬季是进补的大好时机，在进补前吃点山药，更有利于补品的吸收。山药为补中益气药，具有补益脾胃的作用，特别适合脾胃虚弱者进补前食用。山药营养价值较高，既是食用的佳蔬，又是常用的药材。山药为薯蓣科植物薯蓣的块根，具有补脾养胃、补肺益肾的功效。可用于治疗脾虚久泻、慢性肠炎、肺虚咳喘、慢性胃炎、糖尿病、遗精、遗尿、带下等症。经常食用可提高机体的免疫力。山药非常适合冬季手脚发凉的女性食用。另外，冬季时人体脂肪长得较快，想减肥的女性可以选择山药作为瘦身帮手，因为山药营养多，热量少，易增加饱腹感。冬季容易感冒和肾虚咳嗽的人也可以经常吃些山药。 猪肉是日常生活的主要副食品，具有补虚强身，滋阴润燥、丰肌泽肤的作用。凡病后体弱、产后血虚、面黄赢瘦者，皆可用之作营养滋补之品。 红烧肉炖山药是一道冬季进补的家常菜，因为山药热量很少加入在美味诱人的红烧肉中可以减少肉的油腻感，而山药浸透了浓郁的肉香，更加入味，绵绵糯糯的非常好吃。我家喜欢吃瘦一点的肉，五花肉有点肥，所以猪肉我选用了五花肉和猪后尖肉，这样炖出的红烧肉瘦肉比较多但是不会柴。","ingredients":"五花肉,200g;猪后尖肉,200g;山药,300g","burden":"油,适量;盐,适量;冰糖,20g;红烧酱油,5g;葱,适量;姜,适量;八角,2粒;桂皮,1块;干辣椒,1个;料酒,1大勺","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/481_358021.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_45d690b39852cfde.jpg","step":"1.将五花肉和��尖肉洗净，切块。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_6c35da5528bbe1ca.jpg","step":"2.准备好八角 桂皮 料酒 葱姜。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_3dba8acbb3aec8fa.jpg","step":"3.山药洗净去皮，切成滚刀块。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_bf2d575b4cfb7033.jpg","step":"4.把山药下入油锅煎成金黄。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_ae4dfc27d2bd6b08.jpg","step":"5.把肉水焯一下，捞出备用。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_2dffaaa16c6d8223.jpg","step":"6. 锅中加入少量的油，下入焯好水的肉煸炒。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_c991c2a4cdcf710c.jpg","step":"7.加入葱姜和八角 桂皮 辣椒，继续煸炒至出香味，肉色微黄。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_d7e1c63292dd77e1.jpg","step":"8.加入冰糖转小火，煸炒至上糖色。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_e9cd3726ad25464d.jpg","step":"9.烹入料酒，加入酱油煸炒均匀。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_c13327de1dfff667.jpg","step":"10.加入开水，没过肉，大火烧开，转小火炖焖40分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_cc4d264b8a1c63c8.jpg","step":"11.加入盐和煎炸好的山药，再炖焖30分钟。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/5/481_35654a487e056f4a.jpg","step":"12.转中火，烧至汤汁浓缩即可。"}]}]
         * totalNum : 306
         * pn : 3
         * rn : 10
         */

        private String totalNum;
        private String pn;
        private String rn;
        private List<DataBean> data;

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getPn() {
            return pn;
        }

        public void setPn(String pn) {
            this.pn = pn;
        }

        public String getRn() {
            return rn;
        }

        public void setRn(String rn) {
            this.rn = rn;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 92
             * title : 红烧肉
             * tags : 家常菜;快手菜
             * imtro : 红烧肉，各地各家的做法稍有不同味道即大不一样，中餐的精妙在于食材随意性而产生的变化，南方习惯用酱油(老抽)调色，而北方则偏爱炒糖色。 女人不要为了保持好身材，刻意与美味的肉类食品绝缘哦。其实，很多科学证明，适当的吃肉并不会增加额外的脂肪。 猪肉经过小火煸炒出油后，炖出来的红炒肉是肥而不腻，软烂入味，非常的好吃美味，也非常适合咱们的健康理念，即少油，又解馋，又解腻，下面，为你推荐这种红烧肉的经典做法，不用一滴油就可以做出美味健康的红烧肉。
             * ingredients : 猪后臀尖,1000g
             * burden : 葱段,适量;八角,2个;干辣椒,4个;香叶,4片;桂皮,1块;鲜姜,1块;干山楂片,4片;黄油,适量;老抽,适量;生抽,适量;白糖,适量;开水,适量
             * albums : ["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/92_512827.jpg"]
             * steps : [{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_472370e29b980b31.jpg","step":"1.猪肉清洗干净切成方块，冷水下锅，水开后撇去上面的浮沫。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_04f90c14513aa139.jpg","step":"2.焯好的肉块捞出，用温水清洗干净。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_50af1fae3911794d.jpg","step":"3.准备好调料：干辣椒，桂皮，香叶，八角，（大蒜也可以不放）。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_12f839ee0920c03b.jpg","step":"4.做锅开小火，不放油，把肉块放入反复煸炒，煸炒至肉块有点焦香，有油渗出，关火把肉块捞出。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_bfdc35cde7da929b.jpg","step":"5.做锅，用肉块煸出的猪油烧热，放入八角，干辣椒，桂皮，香叶煸炒出香味。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_e21dddc1ffcad36b.jpg","step":"6.放入肉块煸炒1-2分钟后捞出备用。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_89ba401801a762fe.jpg","step":"7.用锅中剩下的余油放入2勺白糖（这个量可以根据自己的喜好添加，喜欢吃甜一点的就多放点糖），开小火煸炒白糖。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_33f84ea85dcd11c9.jpg","step":"8.这是糖色变化的过程"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_bbf69240b5f7211d.jpg","step":"9.当糖起小泡，颜色变红就可以了。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_114bbdd5ef3d1255.jpg","step":"10.这时快速烹上1勺热水，加入肉块煸炒至肉块颜色变红。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_831b33673ac8c507.jpg","step":"11.煸炒至猪肉块上色后，加入适量生抽，老抽（不可多放，放多了颜色就发黑了），黄酒煸炒。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_a377c03180cb6b26.jpg","step":"12.加入开水（多放点水，最好一次加足，肉炖熟了可以大火收汤，水少了，中途加水就影响肉质的口感了），再把姜块，葱段，山楂片放入。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_127b81298ad6cf93.jpg","step":"13.盖上锅盖，大火烧开，小火炖煮1个小时。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_59ebcf1a60e54619.jpg","step":"14.炖煮了一个小时了，汤汁也差不多剩一半了，这时加入6克盐（盐可以根据自己的口味添加，在还有汤的情况下，不要尝出咸味正好，等汤汁收浓就会咸了），小火继续炖煮。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_f4cbb0c04220428e.jpg","step":"15.当品尝到肉质软烂，就可以大火收汁，一锅肥而不腻，肉质酥烂，诱人食欲的红烧肉就炖好了，赶快就着小酒，大快朵颐的享受吧"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_372112ff28389c98.jpg","step":"16.炎热的夏季，吃一顿少油，解腻，又美味的红烧肉大餐。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_1630281ff0350105.jpg","step":"17.再来一张，想吃吗，那就赶紧动手吧，呵呵。"}]
             */

//            result> ResultBean> DataBean
            private String id;
            private String title;
            private String tags;
            private String imtro;
            private String ingredients;
            private String burden;
            private List<String> albums;
            private List<StepsBean> steps;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getImtro() {
                return imtro;
            }

            public void setImtro(String imtro) {
                this.imtro = imtro;
            }

            public String getIngredients() {
                return ingredients;
            }

            public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
            }

            public String getBurden() {
                return burden;
            }

            public void setBurden(String burden) {
                this.burden = burden;
            }

            public List<String> getAlbums() {
                return albums;
            }

            public void setAlbums(List<String> albums) {
                this.albums = albums;
            }

            public List<StepsBean> getSteps() {
                return steps;
            }

            public void setSteps(List<StepsBean> steps) {
                this.steps = steps;
            }

            public static class StepsBean {
                /**
                 * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/92_472370e29b980b31.jpg
                 * step : 1.猪肉清洗干净切成方块，冷水下锅，水开后撇去上面的浮沫。
                 */

                private String img;
                private String step;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getStep() {
                    return step;
                }

                public void setStep(String step) {
                    this.step = step;
                }
            }
        }
    }
}
