// 透過 $(id) 來替代 document.getElementById(id)
const $ = (id) => document.getElementById(id);


const renderShipTable = (ships) => {
	const rows = ships.map(ship => `<tr>
                                    <td>${ship.name}</td>
                                    <td>${ship.type}</td>
                                    <td>${ship.length}</td>
                                    <td>${ship.width}</td>
                                    </tr>`).join('');
	const table = `<table class="table table-hover"><thead><tr>
                 <th>名稱</th><th>種類</th><th>長度</th><th>寬度</th>
                 </tr></thead><tbody>${rows}</tbody></table>`;
	$('result').innerHTML = table;
}


// 待 DOM 加載完成後再執行
document.addEventListener("DOMContentLoaded", async () => {

	// 監聽 btn 點擊
	$('todayBtn').addEventListener("click", async (event) => {
		console.log('todayBtn被點擊');
		const response = await fetch('http://localhost:8081/data/today');
		const { state, message, data } = await response.json();
		console.log(state, message, data);
		$('result').innerHTML = data;
	});

	$('lottoBtn').addEventListener("click", async (event) => {
		console.log('lottoBtn被點擊');
		const response = await fetch('http://localhost:8081/data/lotto');
		const { state, message, data } = await response.json();
		console.log(state, message, data);
		$('result').innerHTML = data;
	});

	$('shipBtn').addEventListener("click", async (event) => {
		console.log('shipBtn被點擊');
		const response = await fetch('http://localhost:8081/data/ship');
		const { state, message, data } = await response.json();
		console.log(state, message, data);
		$('result').innerHTML = `name: ${data.name}<br>
                             type: ${data.type}<br>
                             length: ${data.length}<br>
                             width: ${data.width}`;
	});

	$('shipByIdBtn').addEventListener("click", async (event) => {
		console.log('shipByIdBtn被點擊');
		const id = parseInt(window.prompt('請輸入id'));
		const response = await fetch(`http://localhost:8081/data/ship/${id}`);
		const { state, message, data } = await response.json();
		console.log(state, message, data);
		if (state) {
			$('result').innerHTML = `name: ${data.name}<br>
                               type: ${data.type}<br>
                               length: ${data.length}<br>
                               width: ${data.width}`;
		} else {
			$('result').innerHTML = message;
		}
	});

	$('shipsBtn').addEventListener("click", async (event) => {
		console.log('shipsBtn被點擊');
		const response = await fetch('http://localhost:8081/data/ships');
		const { state, message, data } = await response.json();
		console.log(state, message, data);
		renderShipTable(data);
	});

	$('bmiBtn').addEventListener("click", async (event) => {
		console.log('bmiBtn被點擊');
		const h = window.prompt('請輸入身高');
		const w = window.prompt('請輸入體重');
		const response = await fetch(`http://localhost:8081/data/bmi?h=${h}&w=${w}`);
		const { state, message, data } = await response.json();
		if (state) {
			$('result').innerHTML = `身高: ${data.height}<br>
                               體重: ${data.weight}<br>
                               BMI: ${data.bmi}<br>
                               ${data.result}`;
		} else {
			$('result').innerHTML = message;
		}

	});


});