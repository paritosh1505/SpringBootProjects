var htmlval = document.documentElement;
var theme = htmlval.className;
const buttonTheme = document.getElementById("changeTheme");

function setTheme(initailTheme) {
  localStorage.setItem("theme", initailTheme);
}
function getTheme() {
  if (localStorage.getItem(theme) == null) {
    setTheme(theme);
  } else {
    theme = localStorage.getItem(theme);
  }

  return theme ? theme : "light";
}
//setTheme();
let initailTheme = getTheme();
buttonTheme.addEventListener("click", () => {
  changeTheme();
});

function changeTheme() {
  console.log("*", initailTheme);
  const htmlele = document.documentElement;
  const old_theme = htmlele.className;
  let finalTheme = "";
  if (initailTheme == "light") {
    const bodyele = document.body;
    bodyele.style.color = "dark";
    finalTheme = "dark";
    htmlele.classList.replace(old_theme, finalTheme);
    buttonTheme.innerHTML =
      '<i class="fa-solid fa-circle-half-stroke">Dark</i>';
  } else {
    console.log("there");
    const bodyele = document.body;
    bodyele.style.color = "light";
    finalTheme = "light";
    htmlele.classList.replace(old_theme, finalTheme);
    buttonTheme.innerHTML =
      '<i class="fa-solid fa-circle-half-stroke">Light</i>';
  }
  initailTheme = finalTheme;
  setTheme(finalTheme);

  // document.querySelector("html").classList.add(initailTheme);
}
