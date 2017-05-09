var company = {
	name : "Javacream",
	address : {
		city : "Munich",
		street : "Marienplatz"
	},
	info : function() {
		return "The company " + this.name + " resides in " + this.address.city
	}
}

print(company.info())