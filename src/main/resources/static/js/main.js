/* Переключение между добавлением и удалением "отзывчивого" класса в topnav, когда пользователь нажимает на значок */
function myFunction() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}
