/* global flowjs */

window.addEventListener("load", func);

function func(){
    console.log("LOADED");



    var c = new flowjs.DiGraph();
    c.addPaths([
        ["Build", "Backend Integration Tests"],["Build", "Frontend Integration Tests"],

        ["Backend Integration Tests", "Remote tests"],
        ["Frontend Integration Tests", "Remote tests"],

        ["Remote tests", "Quality check"],
        [ "Quality check" , "Artifact deployement"],
        ["Artifact deployement","Production deployement"]
    ]);






    // Advanced Example with loading animation
    var cf = new flowjs.DiFlowChart("canvas3", c);
    cf.draw();
    simuLoad(cf, c);
}


function simuLoad(flowChart, graph){
    var walker = new flowjs.GraphWalker(graph);
    walker.forEach(function(node){
        var start = Math.random() * 1000 * 5;
        var dur = Math.random() * 1000 * 5;
        simulateLoading(node.id, start);
        simulateDoneLoading(node.id, start + dur);
    }, this);


    function simulateLoading(itemId, timeout){
        setTimeout(function(){
            flowChart.updateItem(itemId, function(item){
                item.flowItem.toggleFlashing();
            });
        }, timeout);

    }

    function simulateDoneLoading(itemId, timeout){
        setTimeout(function(){
            flowChart.updateItem(itemId, function(item){
                item.flowItem.toggleFlashing();
                item.flowItem.color = "#00b894";
                if (item.connectors === undefined){return;}
                item.connectors.forEach(function(conn){
                    conn.color = "#00b894";
                });
            });
        }, timeout);
    }
}
