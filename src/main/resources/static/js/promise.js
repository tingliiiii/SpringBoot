/**
 * Promise 的解釋
 * Promise 是一個用來處理非同步操作的物件。它代表一個尚未完成的操作，但最終會成功完成或失敗。

 * Promise 的三種狀態
 * Pending（待定）：初始狀態，操作尚未完成或被拒絕。
 * Fulfilled（已完成）：操作成功完成。
 * Rejected（已拒絕）：操作失敗。

 * 基本用法
 * let promise = new Promise((resolve, reject) => {
 *   // 執行一些非同步操作
 *   if (操作成功) {
 *     resolve('成功的結果'); // 操作成功時調用
 *   } else {
 *     reject('失敗的原因'); // 操作失敗時調用
 *   }
 * });
 * 
 */


// 煮飯 function 五秒鐘
function cookRice() {
	let promise = new Promise((resolve) => {
		setTimeout(() => { resolve('煮飯完畢') }, 5000);
	});
	return promise;
};

// 擺碗筷 function 立即完成
function setTable() {
	console.log('桌椅碗筷準備完成');
}

// 準備晚餐 function
function prepareDinner() {

	console.log('開始準備晚餐');
	// 開始煮飯
	const ricePromise = cookRice();
	// 擺碗筷
	setTable();

	// 當 ricePromise 完成後再開始吃飯
	ricePromise.then((result) => {
		console.log(result);
		console.log('開始吃飯');
	});
};

prepareDinner();