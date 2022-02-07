#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define SUBMITTED 0
#define RUNNING 1
#define FINISHED 2

struct Query 
{
	int query_id; 
	char* query_text;
	int status; 
	int time_elapsed;
	struct Query *next;
	struct Query *prev;

} *head = NULL, *tail = NULL, *curr = NULL;

int qid=1;

void showAll()
{
	int flag=0;
	const char* s[3] = { "Submitted", "Running", "Finished" };
	
	if(!head) 
	{
		printf("\nDatabase is empty...\n");
		return;
	}
	else	
	{	
		curr = head;
		while(curr)
		{
			if(!flag) printf("\n ID's\t| Status\t| Time\t| Queries");
			printf("\n %d\t| %s\t| %d\t| %s", curr->query_id, s[curr->status], curr->time_elapsed, curr->query_text);
			curr = curr->next;
			flag = 1;
		}
		printf("\n");
	}
	
	while(flag)
	{
		printf("\nPress 0 to continue: ");
		scanf("%d", &flag);
	}
	
	curr = NULL;
}

void newQuery()
{
	char query[1000];
	
	printf("\nEnter Query: ");
	scanf("%[^\n]s", query);
	getchar();
	
	struct Query *node;	
	node = (struct Query *)malloc(sizeof(struct Query));
	
	node->query_text = (char *)malloc(sizeof(strlen(query)));
	strcpy(node->query_text, query);
	node->status = SUBMITTED;
	node->time_elapsed = 0;
	node->next = node->prev = NULL;
	
	if(!head)
	{
		node->query_id = qid++;
		head = node;
		tail = node;
	}
	else
	{
		node->query_id = qid++;
		node->prev = tail;
		tail->next = node;
		tail = node;
	}
	
	printf("\n$->new query entry is added with status as submitted\n");	
	
	node = NULL;
}


void updateToRunning()
{
	int id;
	int flag = 1;
	
	if(head)
	{
		curr = head;
		while(curr)
		{
			if(curr->status == SUBMITTED)
			{				
				if(flag) printf("\n ID's\t| QUERY's");
				printf("\n %d\t| %s", curr->query_id, curr->query_text);
				
				flag = 0;
			}
			curr = curr->next;
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
		
		
		curr = head;
		while(curr)
		{
			if(curr->query_id == id && curr->status == SUBMITTED)
			{
				flag = 1;
				curr->status = RUNNING;
				printf("\nid %d's query is now in running stat...\n", id);
				break;
			}
			
			curr = curr->next;
		}
		
		if(!flag) printf("\nWrong id...\n");
	}
	else printf("\nDatabase is empty...\n");
	
	curr = NULL;
}


void updateTimeElapsed()
{
	if(!head)
	{
		printf("\nDatabase is empty...\n");
		return;
	}
	
	curr = head;
	while(curr)
	{		
		if (curr->status == RUNNING)
			curr->time_elapsed++;
		curr = curr->next;
	}

	printf("\nAll running queries time elapsed has been updated...\n");
		
	curr = NULL;
}


void finishQuery()
{
	int id;
	int flag = 1;
	
	if(head)
	{
		curr = head;
		while(curr)
		{
			if(curr->status == RUNNING)
			{				
				if(flag) printf("\n ID's\t| QUERY's");
				printf("\n %d\t| %s", curr->query_id, curr->query_text);
				
				flag = 0;
			}
			curr = curr->next;
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
		
		
		curr = head;
		while(curr)
		{
			if(curr->query_id == id && curr->status == RUNNING)
			{
				flag = 1;
				curr->status = FINISHED;
				printf("\nid %d's query is now in finished stat...\n", id);
				break;
			}
			
			curr = curr->next;
		}
		
		if(!flag) printf("\nWrong id...\n");
	}
	else printf("\nDatabase is empty...\n");
	
	curr = NULL;
}


void removeAllFinished()
{

	if(!head)
	{
		printf("\nDatabase is empty...\n");
		return;
	}
	
	curr = head;
	while(curr)
	{
		if (curr->status == FINISHED)
		{
			struct Query *temp;	
			temp = (struct Query *)malloc(sizeof(struct Query));
			
			if(curr == head && head == tail)
			{
				temp = curr;
				head = tail = NULL;
			}
			else if(curr == head)
			{
				temp = head;
				head = head->next;
				head->prev = NULL;	
			}
			else if(curr == tail)
			{
				temp = tail;
				tail = tail->prev;
				tail->next = NULL;
			}
			else
			{
				temp = curr;
				curr = temp->prev;
				curr->next = temp->next;
				curr = temp->next;
				curr->prev = temp->prev;
			}
			
			free(temp);
		}
		curr = curr->next;
	}

	printf("\nAll finished queries are removed...\n");
	
	curr = NULL;
}


void exitDatabase()
{
	while(head)
	{
		free(head);
		head = head->next;
	}
		
	system("clear");	
	exit(0);
}

int main(int argc, char *argv[])
{
	void (*options[7])() = { newQuery, updateToRunning, updateTimeElapsed, finishQuery, removeAllFinished, showAll, exitDatabase };
	
	int choice;
	
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

