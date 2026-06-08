package kr.co.farmstory.util;

import java.util.Map;

public class ArticleSwitch {

	public static final Map<String, String> GROUP_TITLE = Map.of(
            "crop", "농작물이야기",
            "community", "커뮤니티"
    );

    public static final Map<String, String> CATE_TITLE = Map.of(
            "story", "농작물이야기",
            "grow", "텃밭가꾸기",
            "school", "귀농학교",
            "notice", "공지사항",
            "menu", "오늘의식단",
            "chef", "나도요리사",
            "qna", "1:1 고객문의",
            "faq", "자주묻는질문"
    );

    public static final Map<String, String> NAV_IMAGE = Map.of(
            "story", "sub_nav_tit_cate3_tit1.png",
            "grow", "sub_nav_tit_cate3_tit2.png",
            "school", "sub_nav_tit_cate3_tit3.png",
            "notice", "sub_nav_tit_cate5_tit1.png",
            "menu", "sub_nav_tit_cate5_tit2.png",
            "chef", "sub_nav_tit_cate5_tit3.png",
            "qna", "sub_nav_tit_cate5_tit4.png",
            "faq", "sub_nav_tit_cate5_tit5.png"
    );
}
