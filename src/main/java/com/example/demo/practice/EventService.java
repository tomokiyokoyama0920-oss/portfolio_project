package com.example.demo.practice;
public class EventService {

	/**
	 * 予約を実行するメソッド
	 * 
	 * @param name  名前
	 * @param count 人数
	 * @throws Exception 予約に失敗した場合
	 */
	public void registerEvent(String name, int count) throws Exception {

		// 1. 手動バリデーションの例
		if (name == null || name.isEmpty()) {
			// 名前がない場合は、例外を投げる
			throw new IllegalArgumentException("名前が未入力です");
		}

		// 2. 業務ロジック（本来はDB保存など）
		try {
			int total = calculate(count);
			System.out.println(name + "様の予約を承りました。合計：" + total + "円");

		} catch (ArithmeticException e) {
			// 3. 計算エラー（人数が0以下など）をキャッチして、別のメッセージで投げ直す
			throw new Exception("計算処理でエラーが発生しました: " + e.getMessage());
		}
	}

	private int calculate(int count) {
		if (count <= 0) {
			// 0で割るような計算エラーを模したエラー発生
			throw new ArithmeticException("人数は1名以上である必要があります");
		}
		return count * 1000;
	}
}