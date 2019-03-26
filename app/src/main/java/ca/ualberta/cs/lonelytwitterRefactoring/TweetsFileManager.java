package ca.ualberta.cs.lonelytwitterRefactoring;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class TweetsFileManager {

	private Context context;

	public TweetsFileManager(Context context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	public List<NormalLonelyTweet> loadTweetsRefactoring() {
		List<NormalLonelyTweet> tweets = new ArrayList<NormalLonelyTweet>();

		try {
			FileInputStream fis = context.openFileInput("file.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Object o = ois.readObject();

			if (o instanceof ArrayList) {
				tweets = (ArrayList<NormalLonelyTweet>) o;
			} else {
				Log.i("LonelyTwitter", "Error casting");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return tweets;
	}

	public void saveTweetsRefactoring(List<NormalLonelyTweet> tweets) {
		try {
			FileOutputStream fos = context.openFileOutput("file.sav", 0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(tweets);

			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}