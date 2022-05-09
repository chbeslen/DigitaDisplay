/* Get the documentElement (<html>) to display the page in fullscreen */
var elem = document.documentElement;
//View in fullscreen
function openFullscreen() {
	if (elem.requestFullscreen) {
	   	elem.requestFullscreen();
	} else if (elem.webkitRequestFullscreen) { /* Safari */
		elem.webkitRequestFullscreen();
	}
	homeButtonStatus();
  	document.getElementById('centerButton').onclick = closeFullscreen();
}
//Close fullscreen
function closeFullscreen() {
	homeButtonStatus();
  	if (document.exitFullscreen) {
    	document.exitFullscreen();
  	} else if (document.webkitExitFullscreen) { /* Safari */
    	document.webkitExitFullscreen();
  	} 
	homeButtonStatus();
	document.getElementById('centerButton').onclick = openFullscreen();
}
function homeButtonStatus(){
	var x = document.getElementById("homeButtonDiv");
	if (x.style.visibility == "hidden") {
    	x.style.visibility = "visible";
	} else {
		x.style.visibility = "hidden";
	}
}

//Clock start
function startTime() {
	const today = new Date();
	let h = today.getHours();
	let m = today.getMinutes();
	let s = today.getSeconds();
	m = checkTime(m);
	s = checkTime(s);
	document.getElementById('rightButton').innerHTML = h%13 + ":" + m + ":" + s;
	setTimeout(startTime, 1000);
}
//Date getter
function getDate(){
	const today = new Date();
	document.getElementById('leftButton').innerHTML = today.toDateString(); 
	setTimeout(getDate, 1000);
}
//Time single digit fixer
function checkTime(i) {
  if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
  return i;
}
//Screen size getter
function getWidth(){
	var width = screen.width;
	var pageString = location.search;
	var page = pageString.substring(6, pageString.indexOf("&"));
	var servlet = pageString.substring(pageString.indexOf("&serv=") +6, pageString.length);
	location.href = "/menuBoard/"+servlet+"?page="+page+"&width="+width;
}
//Pages constants
const pages = ['home', 'TV 1 Display','TV 2 Display','TV 3 Display','TV 4 Display', 'TV 5 Display', 'TV 6 Display'];
//Page Counter
var i = 0;
//Go to next page	
function nextPage(){
	if(i == 6)
  		i = 1;
	else
		i++;
	httpRequest(i);
	
}
//Gp to previous page
function previousPage(){
  	if(i == 1)
  		i = 6;
	else
		i--;	
	httpRequest(i);
}
//Home page button page getter
function getPage(j){
	i = j;
	httpRequest(j);
}
//Server request
function httpRequest(i){
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		document.getElementById("home").innerHTML = this.responseText;
	}
  	xhttp.open("POST", "/board",true);//change this from '/menuBoard/board'to '/board' for aws serßver
  	xhttp.setRequestHeader('display',pages[i]);
  	xhttp.send();
}
function homeButton() {
  location.replace("board")
}
