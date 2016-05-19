package com.baseapp.framework.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;

import com.baseapp.framework.bean.FaceText;

public class FaceTextUtils {

//	public static List<FaceText> faceTexts = new ArrayList<FaceText>();
//	static {
//
//		faceTexts.add(new FaceText("\\u1f60a"));
//		faceTexts.add(new FaceText("\\u1f628"));
//		faceTexts.add(new FaceText("\\u1f60d"));
//		faceTexts.add(new FaceText("\\u1f633"));
//		faceTexts.add(new FaceText("\\u1f60e"));
//		faceTexts.add(new FaceText("\\u1f62d"));
//		faceTexts.add(new FaceText("\\u1f60c"));
//		faceTexts.add(new FaceText("\\u1f635"));
//		faceTexts.add(new FaceText("\\u1f634"));
//		faceTexts.add(new FaceText("\\u1f622"));
//		faceTexts.add(new FaceText("\\u1f605"));
//		faceTexts.add(new FaceText("\\u1f621"));
//		faceTexts.add(new FaceText("\\u1f61c"));
//		faceTexts.add(new FaceText("\\u1f600"));
//		faceTexts.add(new FaceText("\\u1f632"));
//		faceTexts.add(new FaceText("\\u1f61f"));
//		faceTexts.add(new FaceText("\\u1f624"));
//		faceTexts.add(new FaceText("\\u1f61e"));
//		faceTexts.add(new FaceText("\\u1f62b"));
//		faceTexts.add(new FaceText("\\u1f623"));
//		faceTexts.add(new FaceText("\\u1f608"));
//		faceTexts.add(new FaceText("\\u1f609"));
//		faceTexts.add(new FaceText("\\u1f62f"));
//		faceTexts.add(new FaceText("\\u1f615"));
//		faceTexts.add(new FaceText("\\u1f630"));
//		faceTexts.add(new FaceText("\\u1f60b"));
//		faceTexts.add(new FaceText("\\u1f61d"));
//		faceTexts.add(new FaceText("\\u1f613"));
//		faceTexts.add(new FaceText("\\u1f603"));
//		faceTexts.add(new FaceText("\\u1f602"));
//		faceTexts.add(new FaceText("\\u1f618"));
//		faceTexts.add(new FaceText("\\u1f612"));
//		faceTexts.add(new FaceText("\\u1f60f"));
//		faceTexts.add(new FaceText("\\u1f636"));
//		faceTexts.add(new FaceText("\\u1f631"));
//		faceTexts.add(new FaceText("\\u1f616"));
//		faceTexts.add(new FaceText("\\u1f629"));
//		faceTexts.add(new FaceText("\\u1f614"));
//		faceTexts.add(new FaceText("\\u1f611"));
//		faceTexts.add(new FaceText("\\u1f61a"));
//		faceTexts.add(new FaceText("\\u1f62a"));
//		faceTexts.add(new FaceText("\\u1f607"));
//		faceTexts.add(new FaceText("\\u1f64a"));
//		faceTexts.add(new FaceText("\\u1f44a"));
//
//		faceTexts.add(new FaceText("\\u1f44e"));
//		faceTexts.add(new FaceText("\\u261d"));
//		faceTexts.add(new FaceText("\\u270c"));
//		faceTexts.add(new FaceText("\\u1f62c"));
//		faceTexts.add(new FaceText("\\u1f637"));
//		faceTexts.add(new FaceText("\\u1f648"));
//		faceTexts.add(new FaceText("\\u1f44c"));
//		faceTexts.add(new FaceText("\\1f44f"));
//		faceTexts.add(new FaceText("\\u270a"));
//		faceTexts.add(new FaceText("\\u1f4aa"));
//		faceTexts.add(new FaceText("\\u1f606"));
//		faceTexts.add(new FaceText("\\u263a"));
//		faceTexts.add(new FaceText("\\u1f649"));
//		faceTexts.add(new FaceText("\\u1f44d"));
//		faceTexts.add(new FaceText("\\u1f64f"));
//		faceTexts.add(new FaceText("\\u270b"));
//		faceTexts.add(new FaceText("\\u2600"));
//		faceTexts.add(new FaceText("\\u2615"));
//		faceTexts.add(new FaceText("\\u26c4"));
//		faceTexts.add(new FaceText("\\u1f4da"));
//		faceTexts.add(new FaceText("\\u1f381"));
//		faceTexts.add(new FaceText("\\u1f389"));
//		faceTexts.add(new FaceText("\\u1f366"));
//		faceTexts.add(new FaceText("\\u2601"));
//		faceTexts.add(new FaceText("\\u2744"));
//		faceTexts.add(new FaceText("\\u26a1"));
//		faceTexts.add(new FaceText("\\u1f4b0"));
//		faceTexts.add(new FaceText("\\u1f382"));
//		faceTexts.add(new FaceText("\\u1f393"));
//		faceTexts.add(new FaceText("\\u1f356"));
//		faceTexts.add(new FaceText("\\u2614"));
//		faceTexts.add(new FaceText("\\u26c5"));
//		faceTexts.add(new FaceText("\\u270f"));
//		faceTexts.add(new FaceText("\\u1f4a9"));
//		faceTexts.add(new FaceText("\\u1f384"));
//		faceTexts.add(new FaceText("\\u1f377"));
//		faceTexts.add(new FaceText("\\u1f3a4"));
//		faceTexts.add(new FaceText("\\u1f3c0"));
//		faceTexts.add(new FaceText("\\u1f004"));
//		faceTexts.add(new FaceText("\\u1f4a3"));
//		faceTexts.add(new FaceText("\\u1f4e2"));
//		faceTexts.add(new FaceText("\\u1f30f"));
//		faceTexts.add(new FaceText("\\u1f36b"));
//		faceTexts.add(new FaceText("\\u1f3b2"));
//		faceTexts.add(new FaceText("\\u1f3c2"));
//		faceTexts.add(new FaceText("\\u1f4a1"));
//		faceTexts.add(new FaceText("\\u1f4a4"));
//		faceTexts.add(new FaceText("\\u1f6ab"));
//		faceTexts.add(new FaceText("\\u1f33b"));
//		faceTexts.add(new FaceText("\\u1f37b"));
//		faceTexts.add(new FaceText("\\u1f3b5"));
//		faceTexts.add(new FaceText("\\u1f3e1"));
//		faceTexts.add(new FaceText("\\u1f4a2"));
//		faceTexts.add(new FaceText("\\u1f4de"));
//		faceTexts.add(new FaceText("\\u1f6bf"));
//		faceTexts.add(new FaceText("\\u1f35a"));
//		faceTexts.add(new FaceText("\\u1f46a"));
//		faceTexts.add(new FaceText("\\u1f47c"));
//		faceTexts.add(new FaceText("\\u1f48a"));
//		faceTexts.add(new FaceText("\\u1f52b"));
//		faceTexts.add(new FaceText("\\u1f339"));
//		faceTexts.add(new FaceText("\\u1f436"));
//		faceTexts.add(new FaceText("\\u1f484"));
//		faceTexts.add(new FaceText("\\u1f46b"));
//		faceTexts.add(new FaceText("\\u1f47d"));
//		faceTexts.add(new FaceText("\\u1f48b"));
//		faceTexts.add(new FaceText("\\u1f319"));
//		faceTexts.add(new FaceText("\\u1f349"));
//		faceTexts.add(new FaceText("\\u1f437"));
//		faceTexts.add(new FaceText("\\u1f494"));
//		faceTexts.add(new FaceText("\\u1f47b"));
//		faceTexts.add(new FaceText("\\u1f47f"));
//		faceTexts.add(new FaceText("\\u1f48d"));
//		faceTexts.add(new FaceText("\\u1f332"));
//		faceTexts.add(new FaceText("\\u1f434"));
//		faceTexts.add(new FaceText("\\u1f451"));
//		faceTexts.add(new FaceText("\\u1f525"));
//		faceTexts.add(new FaceText("\\u2b50"));
//		faceTexts.add(new FaceText("\\u26bd"));
//		faceTexts.add(new FaceText("\\u1f556"));
//		faceTexts.add(new FaceText("\\u23f0"));
//		faceTexts.add(new FaceText("\\u1f601"));
//		faceTexts.add(new FaceText("\\u1f680"));
//
//	}
//
//	public static String parse(String s) {
//		for (FaceText faceText : faceTexts) {
//			s = s.replace("\\" + faceText.text, faceText.text);
//			s = s.replace(faceText.text, "\\" + faceText.text);
//		}
//		return s;
//	}
//
//	/**
//	 * toSpannableString
//	 * 
//	 * @return SpannableString
//	 * @throws
//	 */
//	public static SpannableString toSpannableString(Context context, String text) {
//		if (!TextUtils.isEmpty(text)) {
//			SpannableString spannableString = new SpannableString(text);
//			int start = 0;
//			Pattern pattern = Pattern.compile("\\\\u[a-z0-9]{4}",
//					Pattern.CASE_INSENSITIVE);
//			Matcher matcher = pattern.matcher(text);
//			while (matcher.find()) {
//				String faceText = matcher.group();
//				String key = faceText.substring(1);
//				BitmapFactory.Options options = new BitmapFactory.Options();
//				Bitmap bitmap = BitmapFactory.decodeResource(
//						context.getResources(),
//						context.getResources().getIdentifier(key, "drawable",
//								context.getPackageName()), options);
//				ImageSpan imageSpan = new ImageSpan(context, bitmap);
//				int startIndex = text.indexOf(faceText, start);
//				int endIndex = startIndex + faceText.length();
//				if (startIndex >= 0)
//					spannableString.setSpan(imageSpan, startIndex, endIndex,
//							Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//				start = (endIndex - 1);
//			}
//
//			return spannableString;
//		} else {
//			return new SpannableString("");
//		}
//	}
//
//	public static SpannableString toSpannableString(Context context,
//			String text, SpannableString spannableString) {
//
//		int start = 0;
//		Pattern pattern = Pattern.compile("\\\\u[a-z0-9]{4}",
//				Pattern.CASE_INSENSITIVE);
//		Matcher matcher = pattern.matcher(text);
//		while (matcher.find()) {
//			String faceText = matcher.group();
//			String key = faceText.substring(1);
//			BitmapFactory.Options options = new BitmapFactory.Options();
//			// options.inSampleSize = 2;
//			Bitmap bitmap = BitmapFactory.decodeResource(
//					context.getResources(),
//					context.getResources().getIdentifier(key, "drawable",
//							context.getPackageName()), options);
//			ImageSpan imageSpan = new ImageSpan(context, bitmap);
//			int startIndex = text.indexOf(faceText, start);
//			int endIndex = startIndex + faceText.length();
//			if (startIndex >= 0)
//				spannableString.setSpan(imageSpan, startIndex, endIndex,
//						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//			start = (endIndex - 1);
//		}
//
//		return spannableString;
//	}
	public static List<FaceText> faceTexts = new ArrayList<FaceText>();
	static {
		faceTexts.add(new FaceText("\\ue056"));
		faceTexts.add(new FaceText("\\ue057"));
		faceTexts.add(new FaceText("\\ue058"));
		faceTexts.add(new FaceText("\\ue059"));
		faceTexts.add(new FaceText("\\ue105"));
		faceTexts.add(new FaceText("\\ue106"));
		faceTexts.add(new FaceText("\\ue107"));
		faceTexts.add(new FaceText("\\ue108"));
		faceTexts.add(new FaceText("\\ue401"));
		faceTexts.add(new FaceText("\\ue402"));
		faceTexts.add(new FaceText("\\ue403"));
		faceTexts.add(new FaceText("\\ue404"));
		faceTexts.add(new FaceText("\\ue405"));
		faceTexts.add(new FaceText("\\ue406"));
		faceTexts.add(new FaceText("\\ue407"));
		faceTexts.add(new FaceText("\\ue408"));
		faceTexts.add(new FaceText("\\ue409"));
		faceTexts.add(new FaceText("\\ue40a"));
		faceTexts.add(new FaceText("\\ue40b"));
		faceTexts.add(new FaceText("\\ue40d"));
		faceTexts.add(new FaceText("\\ue40e"));
		faceTexts.add(new FaceText("\\ue40f"));
		faceTexts.add(new FaceText("\\ue410"));
		faceTexts.add(new FaceText("\\ue411"));
		faceTexts.add(new FaceText("\\ue412"));
		faceTexts.add(new FaceText("\\ue413"));
		faceTexts.add(new FaceText("\\ue414"));
		faceTexts.add(new FaceText("\\ue415"));
		faceTexts.add(new FaceText("\\ue416"));
		faceTexts.add(new FaceText("\\ue417"));
		faceTexts.add(new FaceText("\\ue418"));
		faceTexts.add(new FaceText("\\ue41f"));
		faceTexts.add(new FaceText("\\ue00e"));
		faceTexts.add(new FaceText("\\ue421"));
	}

	public static String parse(String s) {
		for (FaceText faceText : faceTexts) {
			s = s.replace("\\" + faceText.text, faceText.text);
			s = s.replace(faceText.text, "\\" + faceText.text);
		}
		return s;
	}

	/** 
	  * toSpannableString
	  * @return SpannableString
	  * @throws
	  */
	public static SpannableString toSpannableString(Context context, String text) {
		if (!TextUtils.isEmpty(text)) {
			SpannableString spannableString = new SpannableString(text);
			int start = 0;
			Pattern pattern = Pattern.compile("\\\\ue[a-z0-9]{3}", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(text);
			while (matcher.find()) {
				String faceText = matcher.group();
				String key = faceText.substring(1);
				BitmapFactory.Options options = new BitmapFactory.Options();
				Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
						context.getResources().getIdentifier(key, "drawable", context.getPackageName()), options);
				ImageSpan imageSpan = new ImageSpan(context, bitmap);
				int startIndex = text.indexOf(faceText, start);
				int endIndex = startIndex + faceText.length();
				if (startIndex >= 0)
					spannableString.setSpan(imageSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				start = (endIndex - 1);
			}
			return spannableString;
		} else {
			return new SpannableString("");
		}
	}

	public static SpannableString toSpannableString(Context context, String text, SpannableString spannableString) {

		int start = 0;
		Pattern pattern = Pattern.compile("\\\\ue[a-z0-9]{3}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			String faceText = matcher.group();
			String key = faceText.substring(1);
			BitmapFactory.Options options = new BitmapFactory.Options();
//			options.inSampleSize = 2;
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
					.getIdentifier(key, "drawable", context.getPackageName()), options);
			ImageSpan imageSpan = new ImageSpan(context, bitmap);
			int startIndex = text.indexOf(faceText, start);
			int endIndex = startIndex + faceText.length();
			if (startIndex >= 0)
				spannableString.setSpan(imageSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			start = (endIndex - 1);
		}

		return spannableString;
	}

}
