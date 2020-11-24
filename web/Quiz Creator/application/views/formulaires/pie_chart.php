<!-- Page dont le but est d'imprimer les résultat d'une question sous forme d'un camembert  -->

<div class="chart-container" style="position: relative; height:auto; width:32vw">
    <div class="pie-chart-container" >
      <canvas id="pie-chart-<?php echo  $chart_data['que_numero'] ?>" ></canvas>
    </div>
  </div>
 
  
   <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>
   <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> 

  <script>
  $(function(){
      var cData = JSON.parse(`<?php echo json_encode($chart_data); ?>`);
    
      var ctx = $("#pie-chart-<?php echo  $chart_data['que_numero'] ?>");

      var question_text = "<?php echo $chart_data['que_libelle']?>"; 


      var data = {
        labels: cData.label,
        datasets: [
          {
            label: "Users Count",
            data: cData.data,
            backgroundColor: [
              "#DEB887",
              "#A9A9A9",
              "#DC143C",
              "#F4A460",
              "#2E8B57",
              "#1D7A46",
              "#CDA776",
            ],
            borderColor: [
              "#CDA776",
              "#989898",
              "#CB252B",
              "#E39371",
              "#1D7A46",
              "#F4A460",
              "#CDA776",
            ],
            borderWidth: [1, 1, 1, 1, 1,1,1]
          }
        ]
      };
 
      //options
      var options = {
        responsive: true,
        title: {
          display: true,
          position: "top",
          text: question_text, 
          fontSize: 18,
          fontColor: "#111"
        },
        legend: {
          display: true,
          position: "bottom",
          labels: {
            fontColor: "#333",
            fontSize: 16
          }
        }
      };
 

      var chart1 = new Chart(ctx, {
        type: "pie",
        data: data,
        options: options
      });
 
  });
   </script>
