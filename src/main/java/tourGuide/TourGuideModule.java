package tourGuide;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gpsUtil.GpsUtil;
import org.springframework.web.reactive.function.client.WebClient;
import rewardCentral.RewardCentral;
import tourGuide.service.RewardsService;
import tourGuide.util.Utilities;

@Configuration
public class TourGuideModule {
	
	@Bean
	public GpsUtil getGpsUtil() {
		return new GpsUtil();
	}
	
	@Bean
	public RewardsService getRewardsService() {
		return new RewardsService(getGpsUtil(), getRewardCentral());
	}
	
	@Bean
	public RewardCentral getRewardCentral() {
		return new RewardCentral();
	}

	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

	@Bean
	public Utilities utilities(){
		return new Utilities();
	}

}
