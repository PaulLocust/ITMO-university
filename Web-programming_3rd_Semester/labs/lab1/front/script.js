function checkForm(element) {
    var x = Number(element.xInput.value);
    var y = Number(element.yInput.value);
    
    fetch('/api/', {//ajax
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            x: x,
            y: y
        }),
    }).then(response=> response.json()).then(data=>{
        const status=data.status
    })
    return false;
}

const table = document.getElementById("resultTable");
const tbody = document.getElementById("megaTbody");