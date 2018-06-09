package data.vocab;

import android.os.Parcel;
import android.os.Parcelable;

import data.vocab.jp.JapaneseDictionaryType;
import data.vocab.models.DictionaryType;

public class RelatedWordEntry implements Parcelable {
    private String relatedWord;
    private DictionaryType dictionaryType;

    public RelatedWordEntry(String relatedWord, DictionaryType dictionaryType) {
        this.relatedWord = relatedWord;
        this.dictionaryType = dictionaryType;
    }

    public RelatedWordEntry(String relatedWord, String dictionaryTypeString) {
        this.relatedWord = relatedWord;
        this.dictionaryType = JapaneseDictionaryType.fromKey(dictionaryTypeString);
    }


    public String getRelatedWord() {
        return relatedWord;
    }

    public void setRelatedWord(String relatedWord) {
        this.relatedWord = relatedWord;
    }

    public DictionaryType getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(JapaneseDictionaryType dictionaryType) {
        this.dictionaryType = dictionaryType;
    }


    // PARCELABLE IMPLEMENTATION
    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(relatedWord);
        parcel.writeString(dictionaryType.toString());
    }

    private RelatedWordEntry(Parcel in) {
        relatedWord = in.readString();
        dictionaryType = JapaneseDictionaryType.fromKey(in.readString());
    }


    public static final Creator<RelatedWordEntry> CREATOR = new Creator<RelatedWordEntry>() {
        @Override
        public RelatedWordEntry createFromParcel(Parcel in) {
            return new RelatedWordEntry(in);
        }

        @Override
        public RelatedWordEntry[] newArray(int size) {
            return new RelatedWordEntry[size];
        }
    };
}