package org.mfwbook.recommend;

import java.util.List;

import org.mfwbook.model.User;
import org.springframework.stereotype.Component;

@Component
public class FriendsRecommendBySys implements RecommendStrategy<User>{

	@Override
	public List<User> recommend(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
