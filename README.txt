1.How does your solution handle malformed or corrupt data?

METHOD:
It validates JSON file by checking Json Syntax Exception.
If file is not valid it skips to the next JSON in line.

IMPROVEMENT:
After checking JSON file for validation, fix the JSON file by adding missing '{' or '}'
If there is a missing data, than iterate through the next most up to data PII update the missing space

2.Is your solution optimized for query latency or throughput?

ANSWER:
My code is not optimized for query latency or throughput, because it does not consider search history, and iterates through all JSON file.

IMPROVEMENT:
Optimize my search by giving temporary token when query is called, so next time when the user wants most up to date PII, it starts from where the user left on.\

3.What would you do differently if the client doesn't send the account ID?

ANSWER:
a. If the client does not send account id, I would than compare the next most unique items within JSON file. (e.g Name, email address, date of birth)
b. I would first find JSON records that hold most similar values, and than using an account id of a similar JSON records, analyze user's history and compare the result. (e.g if User lived in Main Street of Centerville, California, and if updated information shares similar value except user\'92s address than I could assume that user has updated his address however due to a data corruption account_id was not added correctly)

4. If the view gets very large (can no longer fit into memory), how can we modify it to ensure we\'92re still able to look up examples?

If the view gets very large, I could similar optimized the data so that I could only call for the data that I want, or I can rank the data into hierarchy(most unique, to least unique) and optimize my search.