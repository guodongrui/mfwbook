package org.mfwbook.recommend;

import java.util.List;

import org.mfwbook.model.User;

public interface RecommendStrategy<T extends Recommendable> {
	
	List<T> recommend(User user);
	
}
