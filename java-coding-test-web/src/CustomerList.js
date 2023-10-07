import React from "react";
import DataTable from "react-data-table-component";
import './Style.css'

class CustomerList extends React.Component {


    state = {customerId:"",initialAmount:0}

    customerDataColumns = [
        {
            name: 'Cusomer Id',
            selector: row => row.customerId,
        },
        {
            name: 'Name',
            selector: row => row.name,
        },
        {
            name: 'Surname',
            selector: row => row.surname,
        },
        {
            name: 'Number of account',
            selector: row => row.accounts.length,
        },
        {
            name: 'Account',
            button: true,
            cell: row => <button type="button" onClick={()=> this.handleShowAccountClick(row.customerId)}>Show Account Detail</button>,
        },
    ];

    accountDataColumns = [
        {
            name: "Customer Id",
            selector: row => row.customerId
        },
        {
            name: 'Customer Name',
            selector: row => row.customerName
        },
        {
            name: 'Account Id',
            selector: row => row.accountId
        },
        {
            name: 'Account Type',
            selector: row => row.accountType
        },
        {
            name: 'Transactions',
            button: true,
            cell: row => <button type="button" onClick={()=> this.handleShowTrasactionClick(row.accountId)}>Show Transactions</button>,
        }
    ]

    transactionDataColumns = [
        {
            name: "Account Id",
            selector: row => row.account.accountId
        },
        {
            name: "Transaction Id",
            selector: row => row.transactionId
        },
        {
            name: "Transaction Type",
            selector: row => row.transactionType
        },
        {
            name: "Amount",
            selector: row => row.amount
        }
    ]

    loadCustomerDetail = () => {
        fetch('http://localhost:8080/customer',{
            method:'GET',
            headers: {'Accept':'application/json'}
        })
        .then(response=> response.json())
        .then(data => this.setState(()=>{
            console.log(data);
            return {
                customerData: data
            }
        }))
        .catch(error=>{
            console.log(error);
        });
    }

    createCustomerCurrentAccount = (customerId, initialAmount) => {
        if(customerId && initialAmount) {
            fetch('http://localhost:8080/currentaccount/'+customerId+'/'+initialAmount,{
                method:'POST',
            })
            .then(response=> {
                if(response.ok) {
                    return response.json()
                } else {
                    return response.error
                }
            })
            .then(data => {
                console.log(data);
                window.location.reload();
            })
            .catch(error=>{
                console.log('Error: '+error);
            });    
        }
    }

    componentDidMount() {
        window.addEventListener('load', this.loadCustomerDetail);
    }

    componentWillUnmount() {
        window.removeEventListener('load', this.loadCustomerDetail);
    }

    handleChangeCustomerId = (e) => {
        console.log(e.target.value);
        this.setState({customerId: e.target.value});
    }

    handleChangeInitialAmount = (e) => {
        this.setState({initialAmount: e.target.value});
    }

    handleShowAccountClick = (customerId) => {
        fetch('http://localhost:8080/account/'+customerId,{
            method:'GET'
        })
        .then(response=> response.json())
        .then(data => {
            this.setState(()=>{
                console.log(data)
                data.forEach(element => {
                    element.customerId = customerId
                    element.customerName = element.customers.filter((customer)=>{
                        return customer.customerId === customerId
                    })[0].name
                });
                return {
                    accountData:data
                }
            })
        })
        .catch(error=>{
            console.log(error);
        });        
    }

    handleShowTrasactionClick = (accountId) => {
        fetch('http://localhost:8080/transaction/'+accountId,{
            method:'GET'
        })
        .then(response=> response.json())
        .then(data => {
            this.setState(()=>{
                console.log(data)
                return {
                    transactionData:data
                }
            })
        })
        .catch(error=>{
            console.log(error);
        });                
    }

    handleClose = ()=> {
        this.setState(()=>{
            return {
                show:false
            }
        })
    }

    render(){
        return (
            <div className="container">
                <header>Bank Application</header>
                <div>
                    <fieldset>
                        <legend>Current Account Creation Panel</legend>
                        <table>
                            <tbody>
                                <tr>
                                    <td className="text-right">
                                        <div>
                                            <label>Customer Id: </label>
                                            <input type='number' placeholder="Customer Id" value={this.state.customerId} onChange={this.handleChangeCustomerId} />
                                        </div>
                                    </td>
                                    <td>
                                        <input type={"hidden"}/>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="text-right">
                                        <div>
                                            <label>Initial Amount: </label>
                                            <input type='number' placeholder="Initial Amount" value={this.state.initialAmount} onChange={this.handleChangeInitialAmount}/>
                                        </div>
                                    </td>
                                    <td>
                                        <input type='button' value='Create Customer Current Account' onClick={()=> {
                                            this.createCustomerCurrentAccount(this.state.customerId, this.state.initialAmount)
                                        }} />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>                    
                    <fieldset>
                        <legend>Customer Detail</legend>
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        {
                                            this.state.customerData && 
                                            <DataTable columns={this.customerDataColumns} data={this.state.customerData}/>
                                        }
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                    <fieldset>
                        <legend>Account Detail</legend>
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        {
                                            this.state.accountData && 
                                            <DataTable columns={this.accountDataColumns} data={this.state.accountData}/>
                                        }
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                    <fieldset>
                        <legend>Transaction Detail</legend>
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        {
                                            this.state.transactionData && 
                                            <DataTable columns={this.transactionDataColumns} data={this.state.transactionData}/>
                                        }
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>                    
                </div>
            </div>
        );
    }
}

export default CustomerList;
