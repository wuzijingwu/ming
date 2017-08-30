package com.example.yingshi5;

import java.util.ArrayList;

public class Movie {
	public int error_code;
	public String reason;
	public Results result;
	public class Results{
		public String act;
		public String area;
		public String cover;
		public String desc;
		public String dir;
		public String tag;
		public String title;
		public String vdo_status;
		public String year;
		public Playlinks playlinks;
		public ArrayList<Act_s> act_s;
		public ArrayList<Video_Rec> video_rec;
	}
	public class Playlinks{
		public String cntv;
		public String imgo;
		public String qiyi;
		public String qq;
		public String tudou;
		public String youku;
	}
	public class Act_s{
		public String image;
		public String name;
		public String url;
	}
	public class Video_Rec{
		public String cover;
		public String detail_url;
		public String title;
	}
}
