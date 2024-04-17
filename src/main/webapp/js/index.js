function joinPlayerOne()
{
    generateTable();
}

function joinPlayerTwo()
{
    generateTable();

}

// STOLEN
function generateTable() {

    let container = document.body;
    let table = document.createElement('table');

    let numRows = 10;
    let numCols = 10;

    for (let i = 0; i < numRows; i++)
    {
        let row = document.createElement('tr');
        for (let j = 0; j < numCols; j++) 
        {
            let cell = document.createElement('td');
            let button = document.createElement('button');
            button.textContent = 'Button ' + ((i * numCols) + j + 1);
            button.id = 'Button[' + (i * numCols) + ',' + j + ']';
            button.className = 'gamebuttons';
            // button.onclick(generateSubmit(button.id));
            cell.appendChild(button);
            row.appendChild(cell);
        }
        table.appendChild(row);
    }

    container.appendChild(table);

    // generateSubmit();
}

// function generateSubmit()
// {
//     let submit = document.createElement('button');
//
// }

// function tag(ID)
// {
//     button = document.getElementById(ID);
//
//     if (button.style.backgroundColor = "grey")
//     {
//         button.style.backgroundColor = "red"
//     }
//     else
//     {
//         button.style.backgroundColor = "grey"
//     }
// }

