/**
 * 
 */
const Http = new XMLHttpRequest();
const url='/menuBoard/board';
Http.open("GET", url);
Http.send();

Http.onreadystatechange = (e) => {
  console.log(Http.responseText)
}