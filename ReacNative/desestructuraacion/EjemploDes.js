recuperar=()=>{
    let frutas=["pera","manzana","sandia"];
   frutas.push("banana");
   return frutas;
}



testRecuperar=()=>{
    let misfrutas=recuperar();
    let frutaPrimera=misfrutas[0];
    let otraFruta=misfrutas[1];

    console.log("1"+frutaPrimera);
    console.log("2"+otraFruta);
}
//desestruccturacion de arreglos 
testRecuperarDes=()=>{
    let [frutasPrimero,frutaSegunda,frutaTercera]=recuperar();
    console.log("1>>>"+frutasPrimero);
    console.log("2>>>"+frutaSegunda);
    console.log("13>>>"+frutaTercera);

}