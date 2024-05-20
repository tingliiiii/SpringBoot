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
async function prepareDinner() {

	console.log('開始準備晚餐');

	// 開始煮飯
	const ricePromise = cookRice();

	// 擺碗筷
	setTable();

	// 當 ricePromise 完成後再開始吃飯
	const result = await ricePromise;
	console.log(result);
	console.log('開始吃飯');

};

prepareDinner();