function process(quant){

    let value = parseFloat(document.getElementById("quant").value);
    console.log(value);

    value += quant;

    if(value > 0.040){

        document.getElementById("quant").value = value.toFixed(3);

    }else{

        document.getElementById("quant").value = 0.040.toFixed(3);
    

    }
  }