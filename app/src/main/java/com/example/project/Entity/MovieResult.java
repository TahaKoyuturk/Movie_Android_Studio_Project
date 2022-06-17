package com.example.project.Entity;

import android.os.Parcelable;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieResult implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    public final static Creator<MovieResult> CREATOR = new Creator<MovieResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieResult createFromParcel(android.os.Parcel in) {
            return new MovieResult(in);
        }

        public MovieResult[] newArray(int size) {
            return (new MovieResult[size]);
        }

    };

    protected MovieResult(android.os.Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.result, (com.example.project.Entity.Result.class.getClassLoader()));
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeList(result);
    }

    public int describeContents() {
        return 0;
    }

}


