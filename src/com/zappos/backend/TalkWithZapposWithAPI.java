package com.zappos.backend;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class TalkWithZapposWithAPI {

	private static Gson gson = new GsonBuilder().create();

	private PriorityQueue<SearchResult> queue = new PriorityQueue<SearchResult>(
			20, new Comparator<SearchResult>() {
				@Override
				public int compare(SearchResult o1, SearchResult o2) {
					// TODO Auto-generated method stub
					if (o1.getPriceConverted() < o2.getPriceConverted())
						return -1;
					else if (o1.getPriceConverted() > o2.getPriceConverted())
						return 1;

					// return any one of them
					return 0;
				}
			});

	public void talkWithZapposAPI(Integer quantity, Double budget) {

		String[] searchQueris = { "boots", "beauty", "coats", "jewelry",
				"pajamas", "sunglasses", "sweaters", "sunglasses", "watches",
				"handbags" };
		String key = "52ddafbe3ee659bad97fcce7c53592916a6bfd73";
		// talk with service
		String searchURL = "http://api.zappos.com/Search/term/bags?sort={%"
				+ searchQueris[0] + "%22:%22desc%22}&key=" + key;

		for (int i = 0; i < searchQueris.length; i++) {
			System.out.println("http://api.zappos.com/Search/term/bags?sort={%"
					+ searchQueris[i] + "%22:%22desc%22}&key=" + key);
		}
		// hit the url , get json response, API is not working, therefore
		// assuming response from your website
		// once you get response, get Search Result Object and process

		// /Search/term/<SEARCH_TERM>?sort={"<FIELD>":"<ORDER>"}
		String json = "{ \"statusCode\": \"200\", \"results\": [     {    \"styleId\": \"556677\",    \"productId\": \"123456\",    \"brandName\": \"Ugg\",    \"productName\": \"Classic Tall\",    \"thumbnailImageUrl\": \"http://www.zappos.com/images/image.jpg\",    \"originalPrice\": \"$198.00\",    \"price\": \"$198.00\",    \"percentOff\": \"19%\",    \"productUrl\": \"http://www.zappos.com/product/101183/color/381\"  },  {    \"styleId\": \"556678\",    \"productId\": \"123457\",    \"brandName\": \"Ugg\",    \"productName\": \"Classic Short\",    \"thumbnailImageUrl\": \"http://www.zappos.com/images/image.jpg\",     \"originalPrice\": \"$158.00\",    \"price\": \"$140.00\",    \"percentOff\": \"19%\",    \"productUrl\": \"http://www.zappos.com/product/101183/color/381\"  },  {    \"styleId\": \"556679\",    \"productId\": \"234567\",    \"brandName\": \"Frye\",    \"productName\": \"Engineer 12R W\",    \"thumbnailImageUrl\": \"http://www.zappos.com/images/image.jpg\",    \"originalPrice\": \"$300.00\",    \"price\": \"$300.00\",    \"percentOff\": \"19%\",    \"productUrl\": \"http://www.zappos.com/product/101183/color/381\"  } ] }";

		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		JsonArray jsonArray = jsonObject.get("results").getAsJsonArray();

		System.out.println(jsonArray.size());
		System.out.println(jsonArray.getAsJsonArray());

		// convert to search result object
		List<SearchResult> getResults = getJSON(jsonArray);

		for (int i = 0; i < getResults.size(); i++) {
			String price = getResults.get(i).getPrice();
			getResults.get(i).setPriceConverted(
					(Double.parseDouble(price.replace("$", ""))));
			// add it to priority queue
			queue.add(getResults.get(i));
		}

		// print the queue/ return top quantity results
		Double totalCost = 0.0;
		Integer totalQuantity = 0;

		System.out.println("Results:");

		while (!queue.isEmpty()) {
			SearchResult searchResult = queue.poll();
			totalCost += searchResult.getPriceConverted();
			totalQuantity += 1;
			if (totalCost <= budget && totalQuantity <= quantity) {
				System.out.println("Product :" + searchResult.getBrandName()
						+ ",Price: " + searchResult.getPrice());
			} else {
				break;
			}
		}

	}

	public static List<SearchResult> getJSON(JsonElement json) {
		return gson.fromJson(json, new TypeToken<List<SearchResult>>() {
		}.getType());
	}

	public static void main(String[] args) {
		TalkWithZapposWithAPI talkWithZapposWithAPI = new TalkWithZapposWithAPI();
		talkWithZapposWithAPI.talkWithZapposAPI(3, 700.0);
	}

}
