document.querySelector("#image_input").addEventListener("change", function (e) {
  var file = e.target.files[0];
  var reader = new FileReader();
  reader.onload = function () {
    document.getElementById("uploadImagePreview").src = reader.result;
  };
  reader.readAsDataURL(file);
});
