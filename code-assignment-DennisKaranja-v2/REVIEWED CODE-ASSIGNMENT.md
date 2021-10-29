Below are my comments on the the Assignment:

```
1) When a class is Annotated with @Component, usually this means that you are creating custom beans in the application contexts to inject 
   dependencies, but in this class, there are no beans that have been defined.

2) I noticed that the datasource has been autowired, however I'm of the opinion that the Datasource is usually autowired by default in spring. 
   An alternative to the DriverManager facility, a DataSource object is the preferred means of getting a connection.

3) In this specific class I notice that the Datasource class has been instantiated however, 
   I also noticed that the basic Datasource configurations have not been defined i.e. type of DB, the credentials, connector, datasource pool.

4) From the 'getCustomers' method, I quickly noticed the use of a Connection class, where a connection is created and it's not closed after the
   data has been queried. This creates high risk of the application having too many open connections which intern would affect the DB performance.

5) With the above(4) in mind, an alternative to using the Connection class directly, the use of try-with-resources
   or close the 'Connection' in a 'finally' clause would be a good fit for that.

6) In terms of connecting to a Datasource, I would prefer creating HQL queries as opposed to prepared statement or statement intefaces

7) I see that the developer has chosen to create the customers object as opposed to having one entity class that defines the expected variables 
   of the class object an then setting the values to the object once the DB query has been executed.

8) I also noticed that the variable 'lastName' is not implemented/used anywhere in the code.

9) Also, String 'query' declared and re-initialized again using the string values was not necessary since during declaration of string, one can 
   initialize the string values directly as opposed to what is implemented there.

10) In the 'print' method, a logger resource would surffice as compared to the usual 'System.out.print' methodology to get console output. 
    This has also been replicated similarly in the 'printUpper' method as well.

11) In the 'printUpper' method, the while loop has been implemented to run to infinity since the '(true)' will always be true. An alternative 
    solution would be to use a for-loop so as to have the condition for loop.

12) In the 'printUpper' method, inside the 'if-condition' there is an unnecessary semi-colon that can be eliminated.

13) Also within the method in 12-above, it would be better to use the normal base Exception and capture the exception as compared to using 
    a specific exception(IndexOutOfBoundsException) that will only be used/implemented at runtime alone.
	
//----------------------------------------------------------------------------------------------------------------------------------
	
@Component			//This annotation allows Spring to automatically detect our custom beans.
public class MyAction {
    public boolean debug = true;
	
    @Autowired				//Field Injection is not recommended here. DataSource is usually autowired by default.
    public DataSource ds;	

	
    //A COLLECTION represents a group of objects, known as its elements.
    public Collection getCustomers(String firstName, String lastName, String address, String zipCode, String city) throws SQLException 	//The Variable lastName
	{ 

        Connection conn = ds.getConnection();		//Use try-with-resources or close this "Connection" in a "finally" clause.
		
        String query = new String("SELECT * FROM customers where 1=1"); 	//String is redundant. There is no need to re-initalize the String again with the string values again.
		
        if (firstName != null) {
            query = query + " and first_name = '" + firstName + "'";
        }
        if (firstName != null) {
            query = query + " and last_name = '" + firstName + "'";
        }
        if (firstName != null) {
            query = query + " and address = '" + address + "'";
        }
        if (firstName != null) {
            query = query + " and zip_code = '" + zipCode + "'";
        }
        if (firstName != null) {
            query = query + " and city = '" + city + "'";
        }
		
        Statement stmt = conn.createStatement();
		
        ResultSet rs = stmt.executeQuery(query);
		//Close Connection is not done.
	
        List customers = new ArrayList();	//The ArrayList constructs an empty list with an initial capacity of ten.
		
        while (rs.next()) {
            Object[] objects = new Object[] { rs.getString(1), rs.getString(2) };
            if (debug) print(objects, 4);
			
	    //Appends the specified element to the end of this list (optional operation).
            customers.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));		//Customer class does not exit. Class needs to be created and its constructor also created for it to pick values from Customer class.
        }
		
	//Returns a list of the collected data from the database table.
        return customers;
    }

    //Iterates through the printable objects obtained from the collection list.
    public void print(Object[] s, int indent) {
        for (int i=0; i<=indent; i++) System.out.print(' ');		//Replace the output with a logger resource 
        printUpper(s);
    }

    //This method prints to Upper Case the list of Strings obtained from the collection.
    public static void printUpper(Object [] words){
        int i = 0;
        try {
            while (true){		//Infinite loop. Can be replaced with a for loop with condition
			
                if (words[i].getClass() == String.class) {
                    String so = (String)words[i];;				//Remove unneccessary semi-colon
                    so = so.toUpperCase();
                    System.out.println(so);
                }
                i++;
            }
        } catch (IndexOutOfBoundsException e) {	
				//Use the base exception class to capture 
            //iteration complete
        }
    }
}
```
