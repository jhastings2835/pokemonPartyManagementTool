package common;

import java.util.List;

import model.MAbilityEntity;

public class DataBaseUtil {

	public static List<MAbilityEntity> mAbilityEntityList;

	public static String getAbilityNameById(String id) {

		String name = null;

		for (MAbilityEntity mAbilityEntity : mAbilityEntityList) {
			if (mAbilityEntity != null && id.equals(mAbilityEntity.getId())) {
				name = mAbilityEntity.getName();
				break;
			}
		}

		return name;
	}

}
