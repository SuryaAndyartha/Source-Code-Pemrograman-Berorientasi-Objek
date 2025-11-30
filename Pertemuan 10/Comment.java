public class Comment
{
    private String author;
    private String text;
    private int rating;
    private int upvotes;
    private int downvotes;

    /**
     * Create a new comment with the given author, text, and rating.
     * The number of upvotes and downvotes are initially zero.
     *
     * @param author The author's name.
     * @param text The text of the comment.
     * @param rating The rating value, in range [1..5].
     */
    public Comment(String author, String text, int rating)
    {
        this.author = author;
        this.text = text;
        this.rating = rating;
        upvotes = 0;
        downvotes = 0;
    }

    /**
     * Return the author of this comment.
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Return the text of this comment.
     */
    public String getText()
    {
        return text;
    }

    /**
     * Return the rating of this comment.
     */
    public int getRating()
    {
        return rating;
    }

    /**
     * Upvote this comment (mark as more helpful).
     */
    public void upvote()
    {
        upvotes++;
    }

    /**
     * Downvote this comment (mark as less helpful).
     */
    public void downvote()
    {
        downvotes++;
    }

    /**
     * Return the total vote balance: upvotes - downvotes.
     */
    public int getVoteCount()
    {
        return upvotes - downvotes;
    }

    /**
     * Return full details of the comment, including author, rating, text,
     * and the vote balance.
     */
    public String getFullDetails()
    {
        String details;
        details = "Author: " + author + "\n";
        details += "Rating: " + rating + "\n";
        details += "Comment: " + text + "\n";
        details += "Votes: " + getVoteCount() + " (" + upvotes + " up / " + downvotes + " down)";
        return details;
    }
}
