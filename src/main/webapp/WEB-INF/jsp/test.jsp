
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<style>
  .flex {
    display: flex;
    flex-direction: column;
    height: 100vh;
  }

  body {
    margin: 0;
    padding: 0
  }
  .h100 {

    flex-grow: 3
  }


</style>
<html>
<head>
  <title>Тест на дальтонизм</title>
</head>
<body>
<div class="container-fluid flex">
  <div class="row">
    <div class="col-md-12">
      <jsp:include page="header.jsp"></jsp:include>
    </div>
  </div>
  <div class="row h-100">
    <div class="col-md-12 h-100">

      <img id="image" src="${pageContext.request.contextPath}/static/test/1.jpg" style="height: 400px; width: 400px">
      <p id="p">Что Вы видите на картинке? Выберите один вариант ответа:</p>
       <input type="radio" id="aa1" name="r" value="96">
       <input for="1" id="a11" value="96"><br>
       <input type="radio" id="aa2" name="r" value="9">
       <input for="2" id="a22" value="9"><br>
       <input type="radio" id="aa3" name="r" value="5">
       <input for="3" id="a33" value="5">

        <br>
        <br>
      <button type="button" id="btn" onclick="nextSlide()">Далее</button>
      <script>
      let scope = 0;
      let indexCurr = 2;
        function nextSlide(){
        var rarr=document.getElementsByName("r");
        if(rarr[0].checked){
         scope+=2;
        }else if(rarr[1].checked){
          scope++;
        }
        if(indexCurr==2){
            document.querySelector("#a11").value = "9";
            document.querySelector("#a22").value = "8";
            document.querySelector("#a33").value = "5";
        }else if(indexCurr == 3){
            document.querySelector("#a11").value = "Круг и треугольник";
            document.querySelector("#a22").value = "Круг и квадрат";
            document.querySelector("#a33").value = "Разноцветные точки";
          }
        else if(indexCurr==4){
            document.querySelector("#a11").value = "13";
            document.querySelector("#a22").value = "6";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==5){
            document.querySelector("#a11").value = "96";
            document.querySelector("#a22").value = "6";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==6){
            document.querySelector("#a11").value = "136";
            document.querySelector("#a22").value = "66";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==7){
            document.querySelector("#a11").value = "Круг и треугольник";
            document.querySelector("#a22").value = "Треугольник";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==8){
            document.querySelector("#a11").value = "30";
            document.querySelector("#a22").value = "6";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==9){
            document.querySelector("#a11").value = "Круг и треугольник";
            document.querySelector("#a22").value = "Круг";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==10){
            document.querySelector("#a11").value = "66";
            document.querySelector("#a22").value = "6";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==11){
            document.querySelector("#a11").value = "9";
            document.querySelector("#a22").value = "6";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==12){
            document.querySelector("#a11").value = "13";
            document.querySelector("#a22").value = "8";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==13){
            document.querySelector("#a11").value = "36";
            document.querySelector("#a22").value = "3";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==14){
            document.querySelector("#a11").value = "95";
            document.querySelector("#a22").value = "5";
            document.querySelector("#a33").value = "Ничего не вижу";
        }else if(indexCurr==15){
            document.querySelector("#a11").value = "Круг и треугольник";
            document.querySelector("#a22").value = "Треугольник";
            document.querySelector("#a33").value = "Разноцветные точки";
        }
          let imagePath = "http://localhost:8080/static/test/"+indexCurr+".jpg";
          document.querySelector("#image").src = imagePath;
        if(indexCurr<15){
            indexCurr++;
        }else {
            document.querySelector("#image").remove();
            document.querySelector("#btn").remove();
            document.querySelector("#p").remove();
            document.querySelector("#aa1").remove();
            document.querySelector("#a11").remove();
            document.querySelector("#aa2").remove();
            document.querySelector("#a22").remove();
            document.querySelector("#aa3").remove();
            document.querySelector("#a33").remove();
            var articleDiv = document.querySelector("div.col-md-12");
            var elem = document.createElement("h2");
            var elemText = document.createTextNode("Колличество очков " + scope + "/30 Если Вы набрали менее, чем 24 очка из 30, это значит, что у Вас могут быть проблемы с цветовосприятием. Рекомендуем посетить врача офтальмолога в ближайшее время");
            elem.appendChild(elemText);
            articleDiv.appendChild(elem);
        }
      }
      </script>
      <div class="row">
        <div class="col-md-12">
          <jsp:include page="footer.jsp"></jsp:include>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
