const $ = (id) => document.getElementById(id);



document.addEventListener('DOMContentLoaded', () => {

	// 同步
	$('queryButton').addEventListener('click', async (event) => {
		console.log('begin');
		const response = await fetch('http://localhost:8081/data/lottos');
		// console.log(response);
		const { state, message, data } = await response.json();
		console.log(data.map((item) => item).join(' '));
		// 做其他事情（同步）
		console.log('do something');
		console.log('end');
	});

	// 非同步
	$('queryButton2').addEventListener('click', (event) => {
		console.log('begin');
		fetch('http://localhost:8081/data/lottos')
			.then(response => response.json())
			.then(({ state, message, data }) => {
				console.log(data.map((item) => item).join(' '))
				console.log('end');
			});
		// 做其他事情（非同步）
		console.log('do something');
	});

});