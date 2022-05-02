package ca.pmulcahy.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.pmulcahy.messenger.database.DatabaseClass;
import ca.pmulcahy.messenger.model.Profile;

public class ProfileService {
	private static Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	static {
		profiles.put("pmulcahy", new Profile(1, "pmulcahy", "Paul", "Mulcahy"));
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
