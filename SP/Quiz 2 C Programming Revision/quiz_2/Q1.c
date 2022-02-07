#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define MAX_QUERY 25
#define SUBMITTED 0
#define RUNNING 1
#define FINISHED 2

struct Query 
{
	int query_id; 
	char* query_text;
	int status; 
	int time_elapsed;

}*queries[MAX_QUERY];

int count=0;

void showAll()
{
	struct Query* q;
	const char* s[3] = { "Submitted", "Running", "Finished" };
	int flag=1;
	
	if(!count) 
	{
		printf("\nDatabase is empty...\n");
		return;
	}
	else	
	{	
		for(int i=0; i<count; i++)
		{
			q = queries[i];
			
			printf("\nId: %d", q->query_id);
			printf("\nQuery: %s", q->query_text);
			printf("\nStatus: %s", s[q->status]);
			printf("\nTime Elapsed: %d\n", q->time_elapsed);
		}
		printf("\n");
	}
	
	while(flag)
	{
		printf("Press 0 to continue: ");
		scanf("%d", &flag);
	}
}

void newQuery()
{
	int id;
	char query[1000];
	
	if(count >= MAX_QUERY)
	{
		printf("\nDatabase reched to its maximum size...\n");
		return;
	}

	if(!count) id = 1;
	else id = queries[count-1]->query_id + 1;
	
	printf("\nEnter Query: ");
	scanf("%[^\n]s", query);
	getchar();
	
	struct Query *node;	
	node = (struct Query *)malloc(sizeof(struct Query));
	
	node->query_id = id;
	node->query_text = (char *)malloc(sizeof(strlen(query)));
	strcpy(node->query_text, query);
	node->status = SUBMITTED;
	node->time_elapsed = 0;
	
	queries[count++] = node;
	
	printf("\n$->new query entry is added with status as submitted\n");	
}

void updateToRunning()
{
	int id;
	int flag = 1;
	struct Query* q;
	
	if(count > 0)
	{
		for(int i=0; i<count; i++)
		{
			q = queries[i];
			if(q->status == SUBMITTED)
			{
				if(flag) printf("\n ID's\t| QUERY's");
				printf("\n %d\t| %s", q->query_id, q->query_text);
				flag = 0;
			}
		}
		
		if(!flag)
		{
			printf("\n\nEnter Query id to start: ");
			scanf("%d", &id);
			getchar();
		}
		else
		{
			printf("\nNo queries are in submitted stat...\n");
			return;
		}
		
		for(int i=0; i<count; i++)
		{
			q = queries[i];
			if(q->query_id == id && q->status == SUBMITTED)
			{
				q->status = RUNNING;
				flag = 1;
				printf("\nid %d's query is now in running stat...\n", id);
				return;
			}
		}
		
		if(!flag) printf("\nWrong id...\n");
	}
	else printf("\nDatabase is empty...\n");
}

void updateTimeElapsed()
{
	if(!count)
	{
		printf("\nDatabase is empty...\n");
		return;
	}
	
	for(int i=0; i<count; i++)
		if (queries[i]->status == RUNNING)
			queries[i]->time_elapsed++;

	printf("\nAll running queries time elapsed has been updated...\n");
}

void finishQuery()
{
	int id;
	int flag = 1;
	struct Query* q;
	
	if(count > 0)
	{
		
		for(int i=0; i<count; i++)
		{
			q = queries[i];
			if(q->status == RUNNING)
			{
				if(flag) printf("\n ID's\t| QUERY's");
				printf("\n %d\t| %s", q->query_id, q->query_text);
				flag = 0;	
			}
		}
		
		if(!flag)
		{
			printf("\n\nEnter Query id to finish: ");
			scanf("%d", &id);
			getchar();
		}
		else
		{
			printf("\nNo queries are in running stat...\n");
			return;
		}
			
		for(int i=0; i<count; i++)
		{
			q = queries[i];
			if(q->query_id == id && q->status == RUNNING)
			{
				q->status = FINISHED;
				flag = 1;
				printf("\nid %d's query is now in finished stat...\n", id);
				return;
			}
		}
		
		if(!flag) printf("\nWrong id...\n");
	}
	else printf("\nDatabase is empty...\n");
}


void removeAllFinished()
{

	if(!count)
	{
		printf("\nDatabase is empty...\n");
		return;
	}
	
	for(int i=0; i<count; i++)
		if (queries[i]->status == FINISHED)
		{
			free(queries[i]);
			count--;		
			for(int j=i; j<count; j++)
				queries[j] = queries[j+1];
		}

	printf("\nAll finished queries are removed...\n");
}

void exitDatabase()
{
	for(int i=0; i<count; i++)
		free(queries[i]);
		
	system("clear");	
	exit(0);
}

int main(int argc, char *argv[])
{
	void (*options[7])() = { newQuery, updateToRunning, updateTimeElapsed, finishQuery, removeAllFinished, showAll, exitDatabase };
	
	int choice;
	char c;
	
	do
	{
		system("clear");
		printf("\n---------- M E N U ----------");
		printf("\n1) Add New Query");
		printf("\n2) Start Query");
		printf("\n3) Update Query time");
		printf("\n4) Finish Query");
		printf("\n5) Remove All Finished Query");
		printf("\n6) Show All Query");
		printf("\n7) Exit");
		printf("\n-----------------------------");
		printf("\n\nEnter Your Choice: ");
		scanf("%d", &choice);
		getchar();
		
		if(1 <= choice && choice <= 7)
			options[choice-1]();
		else
			printf("\nInvalid Choice..!!!\n");
		
		sleep(1); 
			
	}while(choice != 7);
	
	return 0;
}

