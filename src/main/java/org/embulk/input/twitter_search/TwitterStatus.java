package org.embulk.input.twitter_search;

import java.util.Date;

import twitter4j.Status;
import twitter4j.User;
//import twitter4j.GeoLocation;
//import twitter4j.HashtagEntity;
//import twitter4j.Place;
//import twitter4j.RateLimitStatus;
//import twitter4j.URLEntity;
//import twitter4j.UserMentionEntity;

public class TwitterStatus  {

	private final Status orgStatus;

	public TwitterStatus(Status orgStatus) {
		this.orgStatus = orgStatus;
	}

	public Date getCreatedAt() {
		return this.orgStatus.getCreatedAt();
	}

	public long getId() {
		return this.orgStatus.getId();
	}

	public String getText() {
		return this.orgStatus.getText();
	}

	public User getUser() {
		return orgStatus.getUser();
	}

	//public String getSource() {
	//	return orgStatus.getSource();
	//}
	//
	//public boolean isTruncated() {
	//	return orgStatus.isTruncated();
	//}
	//
	//public long getInReplyToStatusId() {
	//	return orgStatus.getInReplyToStatusId();
	//}
	//
	//public long getInReplyToUserId() {
	//	return orgStatus.getInReplyToUserId();
	//}
	//
	//public String getInReplyToScreenName() {
	//	return orgStatus.getInReplyToScreenName();
	//}
	//
	//public GeoLocation getGeoLocation() {
	//	return orgStatus.getGeoLocation();
	//}
	//
	//public Place getPlace() {
	//	return orgStatus.getPlace();
	//}
	//
	//public boolean isRetweet() {
	//	return orgStatus.isRetweet();
	//}
	//
	//public Status getRetweetedStatus() {
	//	return orgStatus.getRetweetedStatus();
	//}
	//
	//public long[] getContributors() {
	//	return orgStatus.getContributors();
	//}
	//
	//public long getRetweetCount() {
	//	return orgStatus.getRetweetCount();
	//}
	//
	//public boolean isRetweetedByMe() {
	//	return orgStatus.isRetweetedByMe();
	//}
	//
	//public UserMentionEntity[] getUserMentionEntities() {
	//	return orgStatus.getUserMentionEntities();
	//}
	//
	//public URLEntity[] getURLEntities() {
	//	return orgStatus.getURLEntities();
	//}
	//
	//public HashtagEntity[] getHashtagEntities() {
	//	return orgStatus.getHashtagEntities();
	//}
}